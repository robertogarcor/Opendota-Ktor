/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package auth.domain

import com.example.rgc.opendotaktor.users.domain.UserRepositoryImpl
import com.example.rgc.opendotaktor.users.local.LocalDataSource
import com.example.rgc.opendotaktor.users.local.UserLocal
import com.example.rgc.opendotaktor.users.local.UserLocalDataSource

class AuthUserRepositoryImpl() : AuthUserRepository {

    private val localDataSource : LocalDataSource = UserLocalDataSource()
    private val userRepository = UserRepositoryImpl(localDataSource)

    override suspend fun save(user: UserLocal) {
        userRepository.save(user)
    }
}

interface AuthUserRepository {
    suspend fun save(user : UserLocal)
}