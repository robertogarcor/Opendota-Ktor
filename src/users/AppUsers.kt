/*
 * Copyright (c) 2021/9/14
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users

import com.example.rgc.opendotaktor.users.domain.UserRepository
import com.example.rgc.opendotaktor.users.domain.UserRepositoryImpl
import com.example.rgc.opendotaktor.users.domain.users
import com.example.rgc.opendotaktor.users.local.UserLocalDataSource
import io.ktor.application.*
import io.ktor.routing.*

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.user(testing: Boolean = false) {

    val repository : UserRepository = UserRepositoryImpl(UserLocalDataSource())

    routing {
        this.users(repository)
    }

}