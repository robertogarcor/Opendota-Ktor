/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package auth.domain

import com.example.rgc.opendotaktor.auth.AuthJwt
import com.example.rgc.opendotaktor.users.local.UserLocal
import com.google.gson.JsonParser
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.authUsers(authUserRepositoryImpl: AuthUserRepository, autjwt : AuthJwt) {
    post("/register") {
        try {
            val user = call.receive<UserLocal>()
            authUserRepositoryImpl.save(user)
            call.respond(HttpStatusCode.Created, parseJson("{'message' : 'User Created Ok!'}"))
        } catch (e : Exception) {
            call.respond(HttpStatusCode.InternalServerError, parseJson("{'message' : 'Error data register (username or email are exists)'}"))
        }
    }
    post("/login") {
        val login = call.receive<UserLocal>()
        val user = authUserRepositoryImpl.getByUsername(login.username)
        if(user === null || login.password != user.password) {
            call.respond(HttpStatusCode.Unauthorized, parseJson("{'message' : 'Invalid credentials User'}"))
        } else {
            val userToken = user.copy(token = autjwt.token(user.username))
            call.respond(HttpStatusCode.OK, userToken)
            //call.respond(HttpStatusCode.OK, mapOf("token" to autjwt.token(user.username)))
        }
    }
}

private fun parseJson(string : String) = JsonParser.parseString(string)

