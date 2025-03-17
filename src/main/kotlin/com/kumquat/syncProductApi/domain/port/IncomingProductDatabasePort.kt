package com.kumquat.syncProductApi.domain.port

import com.kumquat.syncProductApi.domain.model.product.UpsertProductCommand

interface IncomingProductDatabasePort {
    fun create(upsertProductCommand: UpsertProductCommand)

    fun update(upsertProductCommand: UpsertProductCommand)
}