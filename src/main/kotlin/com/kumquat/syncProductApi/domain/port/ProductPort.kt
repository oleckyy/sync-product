package com.kumquat.syncProductApi.domain.port

import com.kumquat.syncProductApi.domain.model.product.Product
import com.kumquat.syncProductApi.domain.model.product.UpsertProductCommand
import java.util.UUID

interface ProductPort {
    fun create(upsertProductCommand: UpsertProductCommand)

    fun update(upsertProductCommand: UpsertProductCommand)

    fun findAllByStoreIdGroupedByExternalId(externalIds: List<UUID>, storeId:UUID) : Map<UUID, Product>
}