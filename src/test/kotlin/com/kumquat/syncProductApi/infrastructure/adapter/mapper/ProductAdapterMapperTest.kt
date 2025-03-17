package com.kumquat.syncProductApi.infrastructure.adapter.mapper

import com.kumquat.syncProductApi.domain.model.product.UpsertProductCommandFixtures
import com.kumquat.syncProductApi.infrastructure.database.entity.ProductEntityFixtures
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductAdapterMapperTest {
    private val productAdapterMapper = ProductAdapterMapper()

    @Test
    fun `should map to CreatedProductEntity`() {
        //given
        val upsertProductCommand = UpsertProductCommandFixtures.withCompleteData()

        //when
        val result = productAdapterMapper.toCreatedProductEntity(upsertProductCommand)

        //then
        assertThat(result.externalId).isEqualTo(upsertProductCommand.externalId)
        assertThat(result.storeId).isEqualTo(upsertProductCommand.storeId)
        assertThat(result.name).isEqualTo(upsertProductCommand.name)
        assertThat(result.priceNet).isEqualTo(upsertProductCommand.priceNet)
        assertThat(result.vatValue).isEqualTo(upsertProductCommand.vatValue)
        assertThat(result.versionDate).isEqualTo(upsertProductCommand.versionDate)
    }

    @Test
    fun `should map to UpdatedProductEntity`() {
        //given
        val upsertProductCommand = UpsertProductCommandFixtures.withCompleteData()
        val productEntity = ProductEntityFixtures.withCompleteData()

        //when
        val result = productAdapterMapper.toUpdatedProductEntity(upsertProductCommand, productEntity)

        //then
        assertThat(result.id).isEqualTo(productEntity.id)
        assertThat(result.externalId).isEqualTo(upsertProductCommand.externalId)
        assertThat(result.name).isEqualTo(upsertProductCommand.name)
        assertThat(result.priceNet).isEqualTo(upsertProductCommand.priceNet)
        assertThat(result.vatValue).isEqualTo(upsertProductCommand.vatValue)
        assertThat(result.versionDate).isEqualTo(upsertProductCommand.versionDate)
    }

    @Test
    fun `should map to Product`() {
        //given
        val productEntity = ProductEntityFixtures.withCompleteData()

        //when
        val result = productAdapterMapper.toProduct(productEntity)

        //then
        assertThat(result.id).isEqualTo(productEntity.id)
        assertThat(result.externalId).isEqualTo(productEntity.externalId)
        assertThat(result.storeId).isEqualTo(productEntity.storeId)
        assertThat(result.name).isEqualTo(productEntity.name)
        assertThat(result.priceNet).isEqualTo(productEntity.priceNet)
        assertThat(result.vatValue).isEqualTo(productEntity.vatValue)
        assertThat(result.versionDate).isEqualTo(productEntity.versionDate)
    }
}