package com.kumquat.syncProductApi.infrastructure.database

import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntity
import com.kumquat.syncProductApi.infrastructure.database.temporaryModels.StoreVersionInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductEntityRepository : JpaRepository<ProductEntity, UUID> {
    @Query("""
        SELECT NEW
        com.kumquat.syncProductApi.infrastructure.database.temporaryModels.StoreVersionInfo(product.storeId, MAX(product.versionDate))
        FROM ProductEntity product
        GROUP BY product.storeId
    """)
    fun findLatestStoreVersionInfo(): List<StoreVersionInfo>

    fun findAllByStoreIdAndExternalIdIn(storeId:UUID, externalIds: List<UUID>): List<ProductEntity>
}