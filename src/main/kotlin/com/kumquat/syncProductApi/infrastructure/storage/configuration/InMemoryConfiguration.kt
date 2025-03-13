package com.kumquat.syncProductApi.infrastructure.storage.configuration

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryConfiguration {
    @Bean
    fun productLastSynchronizedVersionDates(): MutableMap<UUID, LocalDateTime> = ConcurrentHashMap<UUID, LocalDateTime>()
}