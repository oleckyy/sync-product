package com.kumquat.syncProductApi

import com.kumquat.syncProductApi.tools.DatabaseContainerInitializer
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ActiveProfiles("test-profile")
@ContextConfiguration(initializers = [DatabaseContainerInitializer::class])
class SyncProductApiApplicationTest {
    @Test
    fun `should run application`() {
    }
}