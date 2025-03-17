package com.kumquat.syncProductApi.infrastructure.adapter.mapper

import com.kumquat.syncProductApi.domain.model.store.UpsertStoreCommandFixtures
import com.kumquat.syncProductApi.infrastructure.database.entity.StoreEntityFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StoreAdapterMapperTest {
    private val storeAdapterMapper = StoreAdapterMapper()

    @Test
    fun `should map to CreatedStoreEntity`() {
        //given
        val upsertStoreCommand = UpsertStoreCommandFixtures.withCompleteData()

        //when
        val result = storeAdapterMapper.toCreatedStoreEntity(upsertStoreCommand)

        //then
        assertThat(result.externalId).isEqualTo(upsertStoreCommand.externalId)
        assertThat(result.active).isEqualTo(upsertStoreCommand.active)
        assertThat(result.name).isEqualTo(upsertStoreCommand.name)
        assertThat(result.location).isEqualTo(upsertStoreCommand.location)
        assertThat(result.apiUrl).isEqualTo(upsertStoreCommand.apiUrl)
        assertThat(result.versionDate).isNotNull()
    }

    @Test
    fun `should map to UpdatedStoreEntity`() {
        //given
        val upsertStoreCommand = UpsertStoreCommandFixtures.withCompleteData()
        val storeEntity = StoreEntityFixtures.withCompleteData()

        //when
        val result = storeAdapterMapper.toUpdatedStoreEntity(upsertStoreCommand, storeEntity)

        //then
        assertThat(result.id).isEqualTo(storeEntity.id)
        assertThat(result.externalId).isEqualTo(upsertStoreCommand.externalId)
        assertThat(result.name).isEqualTo(upsertStoreCommand.name)
        assertThat(result.location).isEqualTo(upsertStoreCommand.location)
        assertThat(result.apiUrl).isEqualTo(upsertStoreCommand.apiUrl)
        assertThat(result.versionDate).isAfter(storeEntity.versionDate)
    }

    @Test
    fun `should map to Store`() {
        //given
        val storeEntity = StoreEntityFixtures.withCompleteData()

        //when
        val result = storeAdapterMapper.toStore(storeEntity)

        //then
        assertThat(result.id).isEqualTo(storeEntity.id)
        assertThat(result.externalId).isEqualTo(storeEntity.externalId)
        assertThat(result.name).isEqualTo(storeEntity.name)
        assertThat(result.location).isEqualTo(storeEntity.location)
        assertThat(result.apiUrl).isEqualTo(storeEntity.apiUrl)
        assertThat(result.versionDate).isEqualTo(storeEntity.versionDate)
    }
}