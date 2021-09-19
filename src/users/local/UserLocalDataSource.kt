/*
 * Copyright (c) 2021-2021/9/19
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users.local

import com.example.rgc.opendotaktor.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserLocalDataSource : LocalDataSource {

    override suspend fun save(user: UserLocal) {
        dbQuery {
            Users.insert {
                it[username] = user.username
                it[password] = user.password
                it[firstname] = user.firstname
                it[email] = user.email
                it[description] = user.description
            }
        }
    }

    override suspend fun getByUsername(username: String) : UserLocal?  = dbQuery {
        Users.select {
            Users.username eq username
        }.map { mapperToUserLocal(it) }.singleOrNull()
    }
}


interface LocalDataSource {
    suspend fun save(user : UserLocal)
    suspend fun getByUsername(username : String) : UserLocal?
}