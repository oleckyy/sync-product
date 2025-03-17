package com.kumquat.syncProductApi.domain.port

import com.kumquat.syncProductApi.domain.model.store.Store
import java.util.*

interface OutgoingStoreDatabasePort {
    fun findAllActiveStores(): List<Store>

    fun findAllStoresInGroupedByExternalId(externalIds: List<UUID>): Map<UUID, Store>

    fun existsByExternalId(externalId: UUID): Boolean
}