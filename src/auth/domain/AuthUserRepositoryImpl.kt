/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package auth.domain

import com.example.rgc.opendotaktor.users.domain.UserRepository
import com.example.rgc.opendotaktor.users.local.UserLocal

class AuthUserRepositoryImpl(private val userRepository : UserRepository) : AuthUserRepository {

    override suspend fun save(user: UserLocal) {
        userRepository.save(user)
    }
}

interface AuthUserRepository {
    suspend fun save(user : UserLocal)
}