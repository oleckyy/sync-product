package com.kumquat.syncProductApi.infrastructure.rest.external.service

import com.kumquat.syncProductApi.domain.model.externalProduct.ExternalProductFixtures
import com.kumquat.syncProductApi.domain.model.store.StoreFixtures
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.product.ExternalProductResponse
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.product.ExternalProductResponseFixtures
import com.kumquat.syncProductApi.infrastructure.rest.external.mapper.ExternalProductMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.time.LocalDateTime

class ExternalProductServiceTest {
    private val restTemplate: RestTemplate = mockk()
    private val externalProductMapper: ExternalProductMapper = mockk()
    private val externalProductService = ExternalProductService(restTemplate, externalProductMapper)

    @Test
    fun `should get status 200 and fetch products from external server`() {
        //given
        val store = StoreFixtures.withCompleteData()
        val storeLastSyncDate = LocalDateTime.now()
        val externalProduct = ExternalProductFixtures.withCompleteData()
        val externalProductResponse = ExternalProductResponseFixtures.withCompleteData()
        every { restTemplate.getForEntity<ExternalProductResponse>(any<String>()) } returns ResponseEntity.ok(externalProductResponse)
        every { externalProductMapper.toExternalProduct(any()) } returns externalProduct
        val expected = Result.success(listOf(externalProduct))

        //when
        val result = externalProductService.getExternalProducts(store,storeLastSyncDate)

        //then
        verify(exactly = 1) {
            restTemplate.getForEntity<ExternalProductResponse>(any<String>(), any())
            externalProductMapper.toExternalProduct(any())

        }
        assertThat(result).isEqualTo(expected)
        assertThat(result.isSuccess)
    }

    @Test
    fun `should get status 400 and fail at fetching products from external server`() {
        //given
        val store = StoreFixtures.withCompleteData()
        val storeLastSyncDate = LocalDateTime.now()
        every { restTemplate.getForEntity<ExternalProductResponse>(any<String>()) }returns ResponseEntity.badRequest().build()

        //when
        val result = externalProductService.getExternalProducts(store,storeLastSyncDate)

        //then
        verify(exactly = 1) {
            restTemplate.getForEntity<ExternalProductResponse>(any<String>(), any())
        }
        verify(exactly = 0) {
            externalProductMapper.toExternalProduct(any())
        }
        assertThat(result.isFailure)
    }
}