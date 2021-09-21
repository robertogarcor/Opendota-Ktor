/*
 * Copyright (c) 2021/9/14
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users

import com.example.rgc.opendotaktor.users.domain.UserRepository
import com.example.rgc.opendotaktor.users.domain.users
import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.user(testing: Boolean = false) {

    val repository : UserRepository by inject()

    routing {
        this.users(repository)
    }

}