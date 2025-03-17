package com.kumquat.syncProductApi.infrastructure.adapter

import com.kumquat.syncProductApi.domain.model.store.Store
import com.kumquat.syncProductApi.domain.port.OutgoingStoreDatabasePort
import com.kumquat.syncProductApi.infrastructure.adapter.mapper.StoreAdapterMapper
import com.kumquat.syncProductApi.infrastructure.database.StoreEntityRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

@Service
class OutgoingOutgoingStoreDatabaseDatabaseAdapter(
    private val storeEntityRepository: StoreEntityRepository,
    private val storeAdapterMapper: StoreAdapterMapper
) : OutgoingStoreDatabasePort {

    override fun findAllActiveStores(): List<Store> {
        return storeEntityRepository.findAllByActiveTrue()
            .map { storeAdapterMapper.toStore(it) }
    }

    override fun findAllStoresInGroupedByExternalId(externalIds: List<UUID>): Map<UUID, Store> {
        return storeEntityRepository.findAllByExternalIdIn(externalIds).stream()
            .map { storeAdapterMapper.toStore(it) }
            .collect(Collectors.toMap(Store::externalId, Function.identity()))
    }

    override fun existsByExternalId(externalId: UUID) =
        storeEntityRepository.existsByExternalId(externalId)
}