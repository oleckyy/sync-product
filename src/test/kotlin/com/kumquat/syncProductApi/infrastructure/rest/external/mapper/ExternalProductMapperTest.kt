package com.kumquat.syncProductApi.infrastructure.rest.external.mapper

import com.kumquat.syncProductApi.infrastructure.rest.external.dto.product.ExternalProductDtoFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExternalProductMapperTest {
    private val externalProductMapper = ExternalProductMapper()

    @Test
    fun `should map to ExternalProduct`() {
        //given
        val externalProductDto = ExternalProductDtoFixtures.withCompleteData()

        //when
        val result = externalProductMapper.toExternalProduct(externalProductDto)

        //then
        assertThat(result.id).isEqualTo(externalProductDto.id)
        assertThat(result.name).isEqualTo(externalProductDto.name)
        assertThat(result.priceNet).isEqualTo(externalProductDto.priceNet)
        assertThat(result.vatValue).isEqualTo(externalProductDto.vatValue)
        assertThat(result.versionDate).isEqualTo(externalProductDto.versionDate)
    }
}