/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.auth

import auth.domain.AuthUserRepository
import auth.domain.authUsers
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.auth(testing : Boolean = false) {

    val authJwt = AuthJwt(environment.config.property("ktor.jwt.secret").getString())

    install(Authentication) {
        jwt("auth-jwt"){
            // Configure jwt authentication
            verifier(authJwt.verifier)
            validate {
                UserIdPrincipal(it.payload.getClaim("username").asString())
            }
        }
    }

    val repository : AuthUserRepository by inject()

    routing {
        this.authUsers(repository, authJwt)
    }

}