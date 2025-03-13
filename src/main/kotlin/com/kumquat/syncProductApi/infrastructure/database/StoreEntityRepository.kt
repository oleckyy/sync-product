package com.kumquat.syncProductApi.infrastructure.database

import com.kumquat.syncProductApi.infrastructure.database.entity.StoreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StoreEntityRepository: JpaRepository<StoreEntity, UUID>{
    fun findByExternalId(externalId: UUID): StoreEntity?

    fun findAllByExternalIdIn(externalIds: List<UUID>): List<StoreEntity>

    fun findAllByActiveTrue(): List<StoreEntity>

    fun existsByExternalId(externalId: UUID): Boolean
}