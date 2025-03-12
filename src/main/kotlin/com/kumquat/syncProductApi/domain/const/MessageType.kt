package com.kumquat.syncProductApi.domain.const

enum class MessageType(
    val message: String,
    val type: Int,
) {
    UNKNOWN("Unknown", 0),
    USER_CREATED("Successfully created user.", 100),
    USER_UPDATED("Successfully updated user.", 101),
    USER_ACTIVATED("Successfully activated user.", 102),
    USER_DEACTIVATED("Successfully deactivated user.", 103);

    companion object {
        fun fromType(type: Int) = entries.firstOrNull { it.type == type } ?: UNKNOWN
    }
}