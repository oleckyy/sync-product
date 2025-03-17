package com.kumquat.syncProductApi.infrastructure.rest.external.dto.product

object ExternalProductResponseFixtures {
    fun withCompleteData(
        products: List<ExternalProductDto> = listOf(
            ExternalProductDtoFixtures.withCompleteData(),
        )
    ) = ExternalProductResponse(
        products = products
    )
}