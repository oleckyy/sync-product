package com.kumquat.syncProductApi.infrastructure.rest.external.mapper

import com.kumquat.syncProductApi.infrastructure.rest.external.dto.store.ExternalStoreDtoFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExternalStoreMapperTest {
    private val externalStoreMapper = ExternalStoreMapper()

    @Test
    fun `should map to ExternalStore`() {
        //given
        val externalStoreDto = ExternalStoreDtoFixtures.withCompleteData()

        //when
        val result = externalStoreMapper.toExternalStore(externalStoreDto)

        //then
        assertThat(result.id).isEqualTo(externalStoreDto.id)
        assertThat(result.active).isEqualTo(externalStoreDto.active)
        assertThat(result.name).isEqualTo(externalStoreDto.name)
        assertThat(result.location).isEqualTo(externalStoreDto.location)
        assertThat(result.apiUrl).isEqualTo(externalStoreDto.apiUrl)
        assertThat(result.versionDate).isEqualTo(externalStoreDto.versionDate)
    }
}