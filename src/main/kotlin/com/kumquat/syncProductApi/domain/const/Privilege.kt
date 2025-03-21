package com.kumquat.syncProductApi.domain.const

enum class Privilege(val value: String, val type: Int) {
    UNKNOWN("Unknown", 0),
    ADMIN("ADMIN", 1),
    USER("USER", 2);

    companion object {
        fun fromType(type: Int) = entries.firstOrNull { it.type == type }
            ?: UNKNOWN
        const val ADMIN_ROLE = "ADMIN"
        const val USER_ROLE = "USER"
    }
}
