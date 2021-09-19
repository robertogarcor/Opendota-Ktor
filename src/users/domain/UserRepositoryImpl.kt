/*
 * Copyright (c) 2021-2021/9/19
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users.domain

import com.example.rgc.opendotaktor.users.local.LocalDataSource
import com.example.rgc.opendotaktor.users.local.UserLocal

class UserRepositoryImpl(private val localDataSource: LocalDataSource) : UserRepository {

    override suspend fun save(user: UserLocal) {
        localDataSource.save(user)
    }

    override suspend fun getByUsername(username: String) : UserLocal? {
        return localDataSource.getByUsername(username)
    }
}

interface UserRepository {
    suspend fun save(user : UserLocal)
    suspend fun getByUsername(username : String) : UserLocal?
}