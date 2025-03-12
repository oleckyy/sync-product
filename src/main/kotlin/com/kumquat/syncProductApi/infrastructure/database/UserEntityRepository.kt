package com.kumquat.syncProductApi.infrastructure.database

import com.kumquat.syncProductApi.infrastructure.database.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserEntityRepository : JpaRepository<UserEntity, UUID>{
    fun findByLogin(login: String): UserEntity?
}