package com.kumquat.syncProductApi.infrastructure.storage

import com.kumquat.syncProductApi.domain.model.store.StoreFixtures
import io.mockk.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime
import java.util.*

class ProductSynchronizationStorageTest {
    private val productLastSynchronizedVersionDates: MutableMap<UUID, LocalDateTime> = mockk()
    private val productSynchronizationStorage = ProductSynchronizationStorage(productLastSynchronizedVersionDates)

    @Test
    fun `should get ProductSyncDate By StoreId`() {
        //given
        val store = StoreFixtures.withCompleteData()
        every { productLastSynchronizedVersionDates[any()] } returns store.versionDate

        //when
        val result = productSynchronizationStorage.getProductSyncDateByStoreId(store.id)

        //then
        verify(exactly = 1) {
            productLastSynchronizedVersionDates[any()]
        }
        assertThat(result).isEqualTo(store.versionDate)
    }

    @Test
    fun `should put ProductSyncDateBy StoreId`() {
        //given
        val store = StoreFixtures.withCompleteData()
        every { productLastSynchronizedVersionDates[any()] = any() } just Runs

        //when
        productSynchronizationStorage.putProductSyncDateByStoreId(store.id, store.versionDate)

        //then
        verify(exactly = 1) {
            productLastSynchronizedVersionDates[store.id] = store.versionDate
        }
    }
}