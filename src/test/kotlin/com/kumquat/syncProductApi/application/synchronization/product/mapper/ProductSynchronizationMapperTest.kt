package com.kumquat.syncProductApi.application.synchronization.product.mapper

import com.kumquat.syncProductApi.domain.model.externalProduct.ExternalProductFixtures
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import java.util.*

class ProductSynchronizationMapperTest {
    private val productSynchronizationMapper = ProductSynchronizationMapper()

    @Test
    fun `should map to UpsertProductCommand`() {
        // given
        val productId = UUID.randomUUID()
        val storeId = UUID.randomUUID()
        val externalProduct = ExternalProductFixtures.withCompleteData()

        // when
        val result = productSynchronizationMapper.toUpsertProductCommand(externalProduct, productId,storeId)

        // then
        assertThat(result.id).isEqualTo(productId)
        assertThat(result.externalId).isEqualTo(externalProduct.id)
        assertThat(result.storeId).isEqualTo(storeId)
        assertThat(result.name).isEqualTo(externalProduct.name)
        assertThat(result.priceNet).isEqualTo(externalProduct.priceNet)
        assertThat(result.vatValue).isEqualTo(externalProduct.vatValue)
        assertThat(result.versionDate).isEqualTo(externalProduct.versionDate)
    }
}