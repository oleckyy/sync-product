package com.kumquat.syncProductApi.application.synchronization.product

import com.kumquat.syncProductApi.application.synchronization.product.mapper.ProductSynchronizationMapper
import com.kumquat.syncProductApi.domain.accessor.ProductSynchronizationAccessor
import com.kumquat.syncProductApi.domain.client.ExternalProductClient
import com.kumquat.syncProductApi.domain.model.externalProduct.ExternalProductFixtures
import com.kumquat.syncProductApi.domain.model.product.ProductFixtures
import com.kumquat.syncProductApi.domain.model.product.UpsertProductCommandFixtures
import com.kumquat.syncProductApi.domain.model.store.StoreFixtures
import com.kumquat.syncProductApi.domain.port.IncomingProductDatabasePort
import com.kumquat.syncProductApi.domain.port.OutgoingProductDatabasePort
import com.kumquat.syncProductApi.domain.port.OutgoingStoreDatabasePort
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class ProductSynchronizationManagerTest {
    private val outgoingStoreDatabasePort: OutgoingStoreDatabasePort = mockk()
    private val outgoingProductDatabasePort: OutgoingProductDatabasePort = mockk()
    private val incomingProductDatabasePort: IncomingProductDatabasePort = mockk()
    private val externalProductClient: ExternalProductClient = mockk()
    private val productSynchronizationAccessor: ProductSynchronizationAccessor = mockk()
    private val productSynchronizationMapper: ProductSynchronizationMapper = mockk()
    private val productSynchronizationManager = ProductSynchronizationManager(
        outgoingStoreDatabasePort,
        outgoingProductDatabasePort,
        incomingProductDatabasePort,
        externalProductClient,
        productSynchronizationAccessor,
        productSynchronizationMapper
    )

    @Test
    fun `should fail at synchronization`() {
        runTest {
            // given
            val store = StoreFixtures.withCompleteData()
            coEvery { outgoingStoreDatabasePort.findAllActiveStores() } returns listOf(store)
            coEvery { productSynchronizationAccessor.getProductSyncDateByStoreId(any()) } returns null

            // when
            assertThrows<IllegalStateException> { productSynchronizationManager.synchronize() }

            // then
            coVerify(exactly = 1) {
                productSynchronizationAccessor.getProductSyncDateByStoreId(any())
                outgoingStoreDatabasePort.findAllActiveStores()
            }
        }
    }

    @Test
    fun `should synchronize and create products`() {
        runTest {
            //given
            val store = StoreFixtures.withCompleteData()
            val product = ProductFixtures.withCompleteData()
            val externalProducts = listOf(ExternalProductFixtures.withVersionDate(LocalDateTime.now().plusDays(2)))
            val foundProducts = mapOf(Pair(product.externalId, product))
            val upsertProductCommand = UpsertProductCommandFixtures.withCompleteData()
            coEvery { outgoingStoreDatabasePort.findAllActiveStores() } returns listOf(store)
            coEvery { productSynchronizationAccessor.getProductSyncDateByStoreId(store.id) } returns LocalDateTime.now()
            coEvery { outgoingProductDatabasePort.findAllByStoreIdGroupedByExternalId(any(),any()) } returns foundProducts
            coEvery { externalProductClient.getExternalProducts(any(),any()) } returns Result.success(externalProducts)
            coEvery { incomingProductDatabasePort.create(any()) } just Runs
            coEvery { productSynchronizationMapper.toUpsertProductCommand(any(),null,any()) } returns upsertProductCommand

            //when
            productSynchronizationManager.synchronize()

            //then
            coVerify(exactly = 1) {
                outgoingStoreDatabasePort.findAllActiveStores()
                productSynchronizationAccessor.getProductSyncDateByStoreId(store.id)
                outgoingProductDatabasePort.findAllByStoreIdGroupedByExternalId(any(),any())
                externalProductClient.getExternalProducts(any(),any())
                incomingProductDatabasePort.create(any())
                productSynchronizationMapper.toUpsertProductCommand(any(),null,any())
            }
        }
    }

    @Test
    fun `should synchronize and update products`() {
        runTest {
            //given
            val store = StoreFixtures.withCompleteData()
            val externalProduct = ExternalProductFixtures.withVersionDate(LocalDateTime.now().plusDays(2))
            val product = ProductFixtures.withExternalId(externalProduct.id)
            val externalProducts = listOf(externalProduct)
            val foundProducts = mapOf(Pair(product.externalId, product))
            val upsertProductCommand = UpsertProductCommandFixtures.withCompleteData()
            coEvery { outgoingStoreDatabasePort.findAllActiveStores() } returns listOf(store)
            coEvery { productSynchronizationAccessor.getProductSyncDateByStoreId(store.id) } returns LocalDateTime.now()
            coEvery { outgoingProductDatabasePort.findAllByStoreIdGroupedByExternalId(any(),any()) } returns foundProducts
            coEvery { externalProductClient.getExternalProducts(any(),any()) } returns Result.success(externalProducts)
            coEvery { incomingProductDatabasePort.update(any()) } just Runs
            coEvery { productSynchronizationMapper.toUpsertProductCommand(any(),any(),any()) } returns upsertProductCommand

            //when
            productSynchronizationManager.synchronize()

            //then
            coVerify(exactly = 1) {
                outgoingStoreDatabasePort.findAllActiveStores()
                productSynchronizationAccessor.getProductSyncDateByStoreId(store.id)
                outgoingProductDatabasePort.findAllByStoreIdGroupedByExternalId(any(),any())
                externalProductClient.getExternalProducts(any(),any())
                incomingProductDatabasePort.update(any())
                productSynchronizationMapper.toUpsertProductCommand(any(),any(),any())
            }
        }
    }
}