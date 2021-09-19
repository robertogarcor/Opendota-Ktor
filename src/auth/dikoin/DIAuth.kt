/*
 * Copyright (c) 2021/9/19
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.auth.dikoin

import auth.domain.AuthUserRepository
import auth.domain.AuthUserRepositoryImpl
import com.example.rgc.opendotaktor.users.domain.UserRepository
import com.example.rgc.opendotaktor.users.domain.UserRepositoryImpl
import com.example.rgc.opendotaktor.users.local.LocalDataSource
import com.example.rgc.opendotaktor.users.local.UserLocalDataSource
import org.koin.dsl.module

val authModule = module {
    single<LocalDataSource> { UserLocalDataSource() }
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
    single<AuthUserRepository> {
        AuthUserRepositoryImpl(get())
    }
}
