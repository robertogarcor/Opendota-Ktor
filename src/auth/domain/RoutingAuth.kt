/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package auth.domain

import com.example.rgc.opendotaktor.auth.AuthJwt
import com.example.rgc.opendotaktor.users.local.UserLocal
import com.example.rgc.opendotaktor.utils.Utils
import com.google.gson.JsonParser
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.mindrot.jbcrypt.BCrypt

fun Routing.authUsers(authUserRepositoryImpl: AuthUserRepository, autjwt : AuthJwt) {
    post("/register") {
        try {
            val user = call.receive<UserLocal>()
            user.password = BCrypt.hashpw(user.password, BCrypt.gensalt())
            authUserRepositoryImpl.save(user)
            call.respond(HttpStatusCode.Created, Utils.parseJson("{'message' : 'User Created Ok!'}"))
        } catch (e : Exception) {
            call.respond(HttpStatusCode.InternalServerError, Utils.parseJson("{'message' : 'Error data register (username or email are exists)'}"))
        }
    }
    post("/login") {
        val login = call.receive<UserLocal>()
        val user = authUserRepositoryImpl.getByUsername(login.username)
        if(user === null || !BCrypt.checkpw(login.password, user.password)) {
            call.respond(HttpStatusCode.Unauthorized, Utils.parseJson("{'message' : 'Invalid credentials User'}"))
        } else {
            val userToken = user.copy(token = autjwt.token(user.username))
            call.respond(HttpStatusCode.OK, userToken)
        }
    }
}


