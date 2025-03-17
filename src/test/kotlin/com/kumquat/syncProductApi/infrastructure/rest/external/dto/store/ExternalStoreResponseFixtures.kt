package com.kumquat.syncProductApi.infrastructure.rest.external.dto.store

object ExternalStoreResponseFixtures {
    fun withCompleteData(
        stores: List<ExternalStoreDto> = listOf(
            ExternalStoreDtoFixtures.withCompleteData(),
        )
    ) = ExternalStoreResponse(
        stores = stores
    )
}