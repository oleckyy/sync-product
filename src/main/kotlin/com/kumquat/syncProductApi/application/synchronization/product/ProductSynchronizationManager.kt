package com.kumquat.syncProductApi.application.synchronization.product

import com.kumquat.syncProductApi.application.synchronization.product.mapper.ProductSynchronizationMapper
import com.kumquat.syncProductApi.domain.accessor.ProductSynchronizationAccessor
import com.kumquat.syncProductApi.domain.client.ExternalProductClient
import com.kumquat.syncProductApi.domain.model.externalProduct.ExternalProduct
import com.kumquat.syncProductApi.domain.port.IncomingProductDatabasePort
import com.kumquat.syncProductApi.domain.port.OutgoingProductDatabasePort
import com.kumquat.syncProductApi.domain.port.OutgoingStoreDatabasePort
import jakarta.transaction.Transactional
import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import java.util.UUID

@Service
@Transactional
class ProductSynchronizationManager(
    private val outgoingStoreDatabasePort: OutgoingStoreDatabasePort,
    private val outgoingProductDatabasePort: OutgoingProductDatabasePort,
    private val incomingProductDatabasePort: IncomingProductDatabasePort,
    private val externalProductClient: ExternalProductClient,
    private val productSynchronizationAccessor: ProductSynchronizationAccessor,
    private val productSynchronizationMapper: ProductSynchronizationMapper
) {
    suspend fun synchronize() {
        val stores = outgoingStoreDatabasePort.findAllActiveStores()
        stores.map { store ->
            coroutineScope {
                async {
                    productSynchronizationAccessor.getProductSyncDateByStoreId(store.id)
                        ?.let {
                            async(Dispatchers.IO) {
                                externalProductClient.getExternalProducts(
                                    store,
                                    it,
                                )
                            }.resolve(store.id)
                        }
                        ?: throw IllegalStateException("Nie znaleziono daty dla stacji o ID: ${store.id}")
                }
            }
        }.awaitAll()
    }

    private suspend fun Deferred<Result<List<ExternalProduct>>>.resolve(storeId: UUID) {
        this.await()
            .onSuccess { externalProducts ->
                createOrUpdateProducts(externalProducts, storeId)
            }
    }

    private fun createOrUpdateProducts(externalProducts: List<ExternalProduct>, storeId: UUID) {
        val externalProductIds = externalProducts.map { it.id }
        val foundProducts = outgoingProductDatabasePort.findAllByStoreIdGroupedByExternalId(externalProductIds, storeId)
        externalProducts.forEach { externalProduct ->
            foundProducts[externalProduct.id]
                ?.takeIf { foundProduct -> externalProduct.versionDate.isAfter(foundProduct.versionDate) }
                ?.let {
                    incomingProductDatabasePort.update(
                        productSynchronizationMapper.toUpsertProductCommand(
                            externalProduct,
                            it.id,
                            storeId
                        )
                    )
                }
                ?: let {
                    incomingProductDatabasePort.create(
                        productSynchronizationMapper.toUpsertProductCommand(
                            externalProduct,
                            null,
                            storeId
                        )
                    )
                }
        }

    }
}