/*
 * Copyright (c) 2021/9/20
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import java.util.*

class AuthJwt(secret: String) {

    private val validityInMs = 36_000_00 * 1
    private val algorithm = Algorithm.HMAC256(secret)

    val verifier: JWTVerifier = JWT.require(algorithm).build()

    fun token (username : String) = JWT.create()
        .withClaim("username", username)
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)
}