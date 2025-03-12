package com.kumquat.syncProductApi.infrastructure.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.config.ScheduledTaskRegistrar

@Configuration
@EnableScheduling
class SchedulingConfiguration : SchedulingConfigurer {
    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        val taskScheduler: ThreadPoolTaskScheduler =
            object : ThreadPoolTaskScheduler() {
                override fun destroy() {
                    this.scheduledThreadPoolExecutor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false)
                    super.destroy()
                }
            }
        taskScheduler.poolSize = POOL_SIZE
        taskScheduler.setWaitForTasksToCompleteOnShutdown(WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN)
        taskScheduler.setAwaitTerminationSeconds(AWAIT_TERMINATION_SECONDS)
        taskScheduler.initialize()
        taskRegistrar.setTaskScheduler(taskScheduler)
    }

    companion object {
        private const val POOL_SIZE = 15
        private const val WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN = true
        private const val AWAIT_TERMINATION_SECONDS = 15
    }
}