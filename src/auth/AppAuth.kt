/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.auth

import auth.domain.AuthUserRepository
import auth.domain.authUsers
import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.auth(testing : Boolean = false) {

    val repository : AuthUserRepository by inject()

    routing {
        this.authUsers(repository)
    }

}