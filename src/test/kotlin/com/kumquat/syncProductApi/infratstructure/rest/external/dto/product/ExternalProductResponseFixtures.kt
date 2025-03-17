package com.kumquat.syncProductApi.infratstructure.rest.external.dto.product

import com.kumquat.syncProductApi.infrastructure.rest.external.dto.product.ExternalProductDto
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.product.ExternalProductResponse

object ExternalProductResponseFixtures {
    fun withCompleteData(
        products: List<ExternalProductDto> = listOf(
            ExternalProductDtoFixtures.withCompleteData(),
            ExternalProductDtoFixtures.withCompleteData(name = "Product 2")
        )
    ) = ExternalProductResponse(
        products = products
    )
}