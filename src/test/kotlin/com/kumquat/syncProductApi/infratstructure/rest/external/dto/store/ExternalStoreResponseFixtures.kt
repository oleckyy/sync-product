package com.kumquat.syncProductApi.infratstructure.rest.external.dto.store

import com.kumquat.syncProductApi.infrastructure.rest.external.dto.store.ExternalStoreDto
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.store.ExternalStoreResponse
import java.util.*

object ExternalStoreResponseFixtures {
    fun withCompleteData(
        stores: List<ExternalStoreDto> = listOf(
            ExternalStoreDtoFixtures.withCompleteData(),
            ExternalStoreDtoFixtures.withCompleteData(id = UUID.randomUUID(), name = "Store ABC")
        )
    ) = ExternalStoreResponse(
        stores = stores
    )
}