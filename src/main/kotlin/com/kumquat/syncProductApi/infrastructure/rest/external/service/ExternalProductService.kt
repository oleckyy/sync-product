package com.kumquat.syncProductApi.infrastructure.rest.external.service

import com.kumquat.syncProductApi.domain.client.ExternalProductClient
import com.kumquat.syncProductApi.domain.model.externalProduct.ExternalProduct
import com.kumquat.syncProductApi.domain.model.store.Store
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.product.ExternalProductDto
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.product.ExternalProductResponse
import com.kumquat.syncProductApi.infrastructure.rest.external.mapper.ExternalProductMapper
import com.kumquat.syncProductApi.util.logger
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDateTime

@Service
class ExternalProductService(
    private val restTemplate: RestTemplate,
    private val externalProductMapper: ExternalProductMapper
) : ExternalProductClient {
    override fun getExternalProducts(store: Store, storeLastSyncDate: LocalDateTime): Result<List<ExternalProduct>> {
        return runCatching {
            createUrl(store.apiUrl, storeLastSyncDate.toString())
                .let { url ->
                    val externalProductResponse = restTemplate.getForEntity<ExternalProductResponse>(url)
                    if (externalProductResponse.statusCode == HttpStatus.OK && externalProductResponse.hasBody())
                        return Result.success(externalProductResponse.body!!.products.toExternalProducts())
                }
            return Result.failure(IllegalStateException("Failed to download products from store! Store name: [${store.name}]."))
        }.onFailure { exception ->
            log.warn("Failed to download products from store! Store name: [${store.name}]", exception)
        }
    }

    private fun List<ExternalProductDto>.toExternalProducts(): List<ExternalProduct> =
        this.map { externalProductMapper.toExternalProduct(it) }


    private fun createUrl(
        url: String,
        paramValue: String
    ) = UriComponentsBuilder.fromHttpUrl("$url$ENDPOINT").queryParam(PARAM_NAME, paramValue).toUriString()


    companion object {
        private val log by logger()
        private const val ENDPOINT = "/product"
        private const val PARAM_NAME = "syncDateTime"
    }
}