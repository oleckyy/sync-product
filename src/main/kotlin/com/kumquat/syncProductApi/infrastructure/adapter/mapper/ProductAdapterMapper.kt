package com.kumquat.syncProductApi.infrastructure.adapter.mapper

import com.kumquat.syncProductApi.domain.model.product.Product
import com.kumquat.syncProductApi.domain.model.product.UpsertProductCommand
import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntity
import org.springframework.stereotype.Component

@Component
class ProductAdapterMapper {
    fun toCreatedProductEntity(upsertProductCommand: UpsertProductCommand) =
        ProductEntity(
            externalId = upsertProductCommand.externalId,
            storeId = upsertProductCommand.storeId,
            name = upsertProductCommand.name,
            priceNet = upsertProductCommand.priceNet,
            vatValue = upsertProductCommand.vatValue,
            versionDate = upsertProductCommand.versionDate,
        )

    fun toUpdatedProductEntity(upsertProductCommand: UpsertProductCommand, productEntity: ProductEntity) =
        productEntity.copy(
            externalId = upsertProductCommand.externalId,
            name = upsertProductCommand.name,
            priceNet = upsertProductCommand.priceNet,
            vatValue = upsertProductCommand.vatValue,
            versionDate = upsertProductCommand.versionDate,
        )

    fun toProduct(productEntity: ProductEntity) =
        Product(
            id = productEntity.id!!,
            externalId = productEntity.externalId,
            storeId = productEntity.storeId,
            name = productEntity.name,
            priceNet = productEntity.priceNet,
            vatValue = productEntity.vatValue,
            versionDate = productEntity.versionDate,
        )
}