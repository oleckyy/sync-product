package com.kumquat.syncProductApi.infrastructure.rest.internal.dto

import com.kumquat.syncProductApi.domain.const.MessageType

data class Response(
    private val message: String,
) {
    companion object {
        fun withMessage(messageType: MessageType): Response {
            return Response(messageType.message)
        }
    }
}
