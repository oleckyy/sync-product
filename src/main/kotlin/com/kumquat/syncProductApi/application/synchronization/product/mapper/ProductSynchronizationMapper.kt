package com.kumquat.syncProductApi.application.synchronization.product.mapper

import com.kumquat.syncProductApi.domain.model.externalProduct.ExternalProduct
import com.kumquat.syncProductApi.domain.model.product.UpsertProductCommand
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProductSynchronizationMapper {
    fun toUpsertProductCommand(
        externalProduct: ExternalProduct,
        productId: UUID?,
        storeId: UUID
    ) =
        UpsertProductCommand(
            id = productId,
            externalId = externalProduct.id,
            storeId = storeId,
            name = externalProduct.name,
            priceNet = externalProduct.priceNet,
            vatValue = externalProduct.vatValue,
            versionDate = externalProduct.versionDate,
        )
}