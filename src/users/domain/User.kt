/*
 * Copyright (c) 2021/9/15
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users.domain

import com.example.rgc.opendotaktor.users.local.UserLocal

data class User(val id : Int,
                val username : String,
                @Transient val password : String,
                val firstname : String,
                @Transient val email : String,
                val description : String,
                val token : String?)


fun mapperToUserDomain(userLocal: UserLocal) : User {
    return User (
        id = userLocal.id,
        username = userLocal.username,
        password = userLocal.password,
        firstname = userLocal.firstname,
        email = userLocal.email,
        description = userLocal.description,
        token = null
    )
}
