/*
 * Copyright (c) 2021/9/21
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users.domain

import com.google.gson.JsonParser
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.users(repository: UserRepository) {
    authenticate("auth-jwt") {
        get("/users") {
            val users: List<User>? = repository.getAllUsers()
            if (users == null) {
                call.respond(HttpStatusCode.NotFound, parseJson("{'message' : 'Users Not Found'}"))
            } else {
                call.respond(HttpStatusCode.OK, users)
            }
        }
    }
}

private fun parseJson(string : String) = JsonParser.parseString(string)