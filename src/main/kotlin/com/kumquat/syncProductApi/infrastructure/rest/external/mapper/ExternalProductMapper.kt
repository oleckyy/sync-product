package com.kumquat.syncProductApi.infrastructure.rest.external.mapper

import com.kumquat.syncProductApi.domain.model.externalProduct.ExternalProduct
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.product.ExternalProductDto
import org.springframework.stereotype.Component

@Component
class ExternalProductMapper {
    fun toExternalProduct(externalProductDto: ExternalProductDto) =
        ExternalProduct(
            id = externalProductDto.id,
            name = externalProductDto.name,
            priceNet = externalProductDto.priceNet,
            vatValue = externalProductDto.vatValue,
            versionDate = externalProductDto.versionDate,
        )
}