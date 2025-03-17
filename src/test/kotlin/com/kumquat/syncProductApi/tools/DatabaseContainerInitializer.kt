package com.kumquat.syncProductApi.tools

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.Profiles
import org.testcontainers.containers.PostgreSQLContainer

class DatabaseContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val acceptedProfiles = Profiles.of("test-profile")
        val environment = applicationContext.environment
        if (environment.acceptsProfiles(acceptedProfiles)) {
            postgreSQLContainer.start()
            testPropertyValues().applyTo(environment)
        }
    }

    private fun testPropertyValues() =
        TestPropertyValues.of(
            "spring.datasource.url=" + postgreSQLContainer.jdbcUrl,
            "spring.datasource.username=" + postgreSQLContainer.username,
            "spring.datasource.password=" + postgreSQLContainer.password,
            "spring.datasource.driver-class-name=" + postgreSQLContainer.driverClassName,
        )

    companion object {
        private const val IMAGE_NAME = "postgres:15.7"
        private const val DATABASE_NAME = "test-db"
        private const val USERNAME = "test-username"
        private const val PASSWORD = "test-password"

        private val postgreSQLContainer: PostgreSQLContainer<*> by lazy {
            PostgreSQLContainer(IMAGE_NAME)
                .withDatabaseName(DATABASE_NAME)
                .withUsername(USERNAME)
                .withPassword(PASSWORD)
        }
    }
}