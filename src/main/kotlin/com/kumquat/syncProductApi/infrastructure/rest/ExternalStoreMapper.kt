package com.kumquat.syncProductApi.infrastructure.rest

import com.kumquat.syncProductApi.domain.model.externalStore.ExternalStore
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.store.ExternalStoreDto
import org.springframework.stereotype.Component

@Component
class ExternalStoreMapper {
    fun toExternalStore(externalStoreDto: ExternalStoreDto) =
        ExternalStore(
            id = externalStoreDto.id,
            active = externalStoreDto.active,
            name = externalStoreDto.name,
            location = externalStoreDto.location,
            apiUrl = externalStoreDto.apiUrl,
        )
}