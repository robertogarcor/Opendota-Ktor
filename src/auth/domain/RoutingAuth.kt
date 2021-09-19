/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package auth.domain

import com.example.rgc.opendotaktor.users.local.UserLocal
import com.google.gson.JsonParser
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.authUser(authUserRepositoryImpl: AuthUserRepository) {
    route ("/register") {
        post() {
            try {
                val user = call.receive<UserLocal>()
                authUserRepositoryImpl.save(user)
                call.respond(HttpStatusCode.Created, parseJson("{'message' : 'User Created Ok!'}"))
            } catch (e : Exception) {
                call.respond(HttpStatusCode.InternalServerError, parseJson("{'message' : 'Error data register (username or email are exists)'}"))
            }
        }
    }
}

private fun parseJson(string : String) = JsonParser.parseString(string)

