/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package auth.domain

import com.example.rgc.opendotaktor.users.domain.User
import com.example.rgc.opendotaktor.users.domain.UserRepository
import com.example.rgc.opendotaktor.users.local.UserLocal

class AuthUserRepositoryImpl(private val userRepository : UserRepository) : AuthUserRepository {

    override suspend fun save(user: UserLocal) {
        userRepository.save(user)
    }

    override suspend fun getByUsername(username: String): User? {
        return userRepository.getByUsername(username)
    }
}

interface AuthUserRepository {
    suspend fun save(user : UserLocal)
    suspend fun getByUsername(username : String) : User?
}