/*
 * Copyright (c) 2021/9/14
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

@Suppress("unused")
@kotlin.jvm.JvmOverloads
fun Application.user(testing: Boolean = false) {

    routing {
        get ("/users") {
            call.respondText { "Users Module OK!" }
        }
    }

}