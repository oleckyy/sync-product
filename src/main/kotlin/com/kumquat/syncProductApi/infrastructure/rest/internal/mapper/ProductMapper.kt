package com.kumquat.syncProductApi.infrastructure.rest.internal.mapper

import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntity
import com.kumquat.syncProductApi.infrastructure.rest.internal.dto.product.ProductDto

fun ProductEntity.toProductDto() =
    ProductDto(
        id = this.id!!,
        externalId = this.externalId,
        storeId = this.storeId,
        name = this.name,
        priceNet = this.priceNet,
        vatValue = this.vatValue,
        versionDate = this.versionDate,
    )