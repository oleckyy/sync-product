package com.kumquat.syncProductApi.domain.port

import com.kumquat.syncProductApi.domain.model.product.Product
import java.util.UUID

interface OutgoingProductDatabasePort {
    fun findAllByStoreIdGroupedByExternalId(externalIds: List<UUID>, storeId:UUID) : Map<UUID, Product>
}