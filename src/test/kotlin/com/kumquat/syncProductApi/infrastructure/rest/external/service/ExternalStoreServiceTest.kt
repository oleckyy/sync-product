package com.kumquat.syncProductApi.infrastructure.rest.external.service

import com.kumquat.syncProductApi.domain.model.externalProduct.ExternalProductFixtures
import com.kumquat.syncProductApi.domain.model.externalStore.ExternalStoreFixtures
import com.kumquat.syncProductApi.domain.model.store.StoreFixtures
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.store.ExternalStoreResponse
import com.kumquat.syncProductApi.infrastructure.rest.external.dto.store.ExternalStoreResponseFixtures
import com.kumquat.syncProductApi.infrastructure.rest.external.mapper.ExternalStoreMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

class ExternalStoreServiceTest {
    private val restTemplate: RestTemplate = mockk()
    private val externalStoreMapper: ExternalStoreMapper = mockk()
    private val externalStoreService = ExternalStoreService(restTemplate, externalStoreMapper)

    @Test
    fun `should get status 200 and fetch stores from external server`() {
        //given
        val url = "http://localhost:8080"
        val externalStore = ExternalStoreFixtures.withCompleteData()
        val externalStoreResponse = ExternalStoreResponseFixtures.withCompleteData()
        every { restTemplate.getForEntity<ExternalStoreResponse>(any<String>()) } returns ResponseEntity.ok(externalStoreResponse)
        every { externalStoreMapper.toExternalStore(any()) } returns externalStore
        val expected = Result.success(listOf(externalStore))

        //when
        val result = externalStoreService.getExternalStores(url)

        //then
        verify(exactly = 1) {
            restTemplate.getForEntity<ExternalStoreResponse>(any<String>(), any())
            externalStoreMapper.toExternalStore(any())

        }
        assertThat(result).isEqualTo(expected)
        assertThat(result.isSuccess)
    }

    @Test
    fun `should get status 400 and fail at fetching stores from external server`() {
        //given
        val url = "http://localhost:8080"
        every { restTemplate.getForEntity<ExternalStoreResponse>(any<String>()) } returns ResponseEntity.badRequest().build()

        //when
        val result = externalStoreService.getExternalStores(url)

        //then
        verify(exactly = 1) {
            restTemplate.getForEntity<ExternalStoreResponse>(any<String>(), any())
        }
        verify(exactly = 0) {
            externalStoreMapper.toExternalStore(any())
        }
        assertThat(result.isFailure)
    }
}