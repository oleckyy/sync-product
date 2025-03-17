package com.kumquat.syncProductApi.application.synchronization.store.mapper

import com.kumquat.syncProductApi.domain.model.externalStore.ExternalStoreFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StoreSynchronizationMapperTest {
    private val storeSynchronizationMapper = StoreSynchronizationMapper()

    @Test
    fun `should map to UpsertStoreCommand`() {
        // given
        val externalStore = ExternalStoreFixtures.withCompleteData()

        // when
        val result = storeSynchronizationMapper.toUpsertStoreCommand(externalStore)

        // then
        assertThat(result.externalId).isEqualTo(externalStore.id)
        assertThat(result.active).isEqualTo(externalStore.active)
        assertThat(result.name).isEqualTo(externalStore.name)
        assertThat(result.location).isEqualTo(externalStore.location)
        assertThat(result.apiUrl).isEqualTo(externalStore.apiUrl)
    }
}