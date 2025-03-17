package com.kumquat.syncProductApi.application.synchronization.store

import com.kumquat.syncProductApi.application.synchronization.store.mapper.StoreSynchronizationMapper
import com.kumquat.syncProductApi.domain.client.ExternalStoreClient
import com.kumquat.syncProductApi.domain.model.externalStore.ExternalStoreFixtures
import com.kumquat.syncProductApi.domain.model.store.StoreFixtures
import com.kumquat.syncProductApi.domain.model.store.UpsertStoreCommandFixtures
import com.kumquat.syncProductApi.domain.port.IncomingStoreDatabasePort
import com.kumquat.syncProductApi.domain.port.OutgoingStoreDatabasePort
import io.mockk.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StoreSynchronizationManagerTest {
    private val outgoingStoreDatabasePort: OutgoingStoreDatabasePort = mockk()
    private val incomingStoreDatabasePort: IncomingStoreDatabasePort = mockk()
    private val externalStoreClient: ExternalStoreClient = mockk()
    private val storeSynchronizationMapper: StoreSynchronizationMapper = mockk()
    private val storeSynchronizationManager = StoreSynchronizationManager(
        outgoingStoreDatabasePort,
        incomingStoreDatabasePort,
        externalStoreClient,
        storeSynchronizationMapper
    )

    @Test
    fun `should synchronize and create stores`() {
        //given
        val externalStore = ExternalStoreFixtures.withCompleteData()
        val store = StoreFixtures.withCompleteData()
        val foundStores = mapOf(Pair(store.externalId, store))
        val upsertStoreCommand = UpsertStoreCommandFixtures.withCompleteData()
        every { externalStoreClient.getExternalStores(any()) } returns Result.success(listOf(externalStore))
        every { outgoingStoreDatabasePort.findAllStoresInGroupedByExternalId(any()) } returns foundStores
        every { storeSynchronizationMapper.toUpsertStoreCommand(any()) } returns upsertStoreCommand
        every { outgoingStoreDatabasePort.existsByExternalId(any()) } returns false
        every { incomingStoreDatabasePort.createStore(any()) } just Runs

        //when
        storeSynchronizationManager.synchronize()

        //then
        verify(exactly = 1) {
            externalStoreClient.getExternalStores(any())
            outgoingStoreDatabasePort.findAllStoresInGroupedByExternalId(any())
            storeSynchronizationMapper.toUpsertStoreCommand(any())
            outgoingStoreDatabasePort.existsByExternalId(any())
            incomingStoreDatabasePort.createStore(any())
        }
    }

    @Test
    fun `should synchronize and not create stores`() {
        //given
        val externalStore = ExternalStoreFixtures.withCompleteData()
        val store = StoreFixtures.withCompleteData()
        val foundStores = mapOf(Pair(store.externalId, store))
        val upsertStoreCommand = UpsertStoreCommandFixtures.withCompleteData()
        every { externalStoreClient.getExternalStores(any()) } returns Result.success(listOf(externalStore))
        every { outgoingStoreDatabasePort.findAllStoresInGroupedByExternalId(any()) } returns foundStores
        every { storeSynchronizationMapper.toUpsertStoreCommand(any()) } returns upsertStoreCommand
        every { outgoingStoreDatabasePort.existsByExternalId(any()) } returns true
        every { incomingStoreDatabasePort.createStore(any()) } just Runs

        //when
        storeSynchronizationManager.synchronize()

        //then
        verify(exactly = 1) {
            externalStoreClient.getExternalStores(any())
            outgoingStoreDatabasePort.findAllStoresInGroupedByExternalId(any())
            storeSynchronizationMapper.toUpsertStoreCommand(any())
            outgoingStoreDatabasePort.existsByExternalId(any())
            incomingStoreDatabasePort.createStore(any())
        }
    }

    @Test
    fun `should synchronize and update stores`() {
        //given
        val store = StoreFixtures.withCompleteData()
        val externalStore = ExternalStoreFixtures.withIdAndVersionDate(store.externalId, store.versionDate.plusDays(2))
        val foundStores = mapOf(Pair(store.externalId, store))
        val upsertStoreCommand = UpsertStoreCommandFixtures.withCompleteData()
        every { externalStoreClient.getExternalStores(any()) } returns Result.success(listOf(externalStore))
        every { outgoingStoreDatabasePort.findAllStoresInGroupedByExternalId(any()) } returns foundStores
        every { storeSynchronizationMapper.toUpsertStoreCommand(any()) } returns upsertStoreCommand
        every { incomingStoreDatabasePort.updateStore(any()) } just Runs

        //when
        storeSynchronizationManager.synchronize()

        //then
        verify(exactly = 1) {
            externalStoreClient.getExternalStores(any())
            outgoingStoreDatabasePort.findAllStoresInGroupedByExternalId(any())
            storeSynchronizationMapper.toUpsertStoreCommand(any())
            incomingStoreDatabasePort.updateStore(any())
        }
    }
}