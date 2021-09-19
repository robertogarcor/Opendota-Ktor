/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.auth

import auth.domain.AuthUserRepository
import auth.domain.AuthUserRepositoryImpl
import auth.domain.authUser
import io.ktor.application.*
import io.ktor.routing.*

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.auth(testing : Boolean = false) {

    val repository : AuthUserRepository = AuthUserRepositoryImpl()

    routing {
        this.authUser(repository)
    }

}