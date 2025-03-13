package com.kumquat.syncProductApi.infrastructure.rest.external.service

import com.kumquat.syncProductApi.domain.client.ExternalStoreClient
import com.kumquat.syncProductApi.domain.model.externalStore.ExternalStore
import com.kumquat.syncProductApi.infrastructure.rest.external.mapper.ExternalStoreMapper
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.store.ExternalStoreDto
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.store.ExternalStoreResponse
import com.kumquat.syncProductApi.util.logger
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class ExternalStoreService(
    private val restTemplate: RestTemplate,
    private val externalStoreMapper: ExternalStoreMapper
) : ExternalStoreClient {
    override fun getExternalStores(url: String): Result<List<ExternalStore>> {
        return runCatching {
            val externalStoreResponse =
                restTemplate.getForEntity(url, ExternalStoreResponse::class.java)
            if (externalStoreResponse.statusCode == HttpStatus.OK && externalStoreResponse.hasBody()) {
                return Result.success(externalStoreResponse.body!!.stores.toExternalStores())
            }
            return Result.failure(RuntimeException("Failed to download stores from hub!"))
        }
            .onFailure { exception ->
                log.warn("Failed to download stores from hub!", exception)
            }
    }

    private fun List<ExternalStoreDto>.toExternalStores() =
        this.map { externalStoreMapper.toExternalStore(it) }

    companion object {
        private val log by logger()
    }
}