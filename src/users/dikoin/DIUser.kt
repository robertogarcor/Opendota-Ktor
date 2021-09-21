/*
 * Copyright (c) 2021/9/21
 * Author : Roberto Garcia
 */

package com.example.rgc.opendotaktor.users.dikoin

import com.example.rgc.opendotaktor.users.domain.UserRepository
import com.example.rgc.opendotaktor.users.domain.UserRepositoryImpl
import com.example.rgc.opendotaktor.users.local.LocalDataSource
import com.example.rgc.opendotaktor.users.local.UserLocalDataSource
import org.koin.dsl.module

val userModule = module {
    single<LocalDataSource> { UserLocalDataSource() }
    single<UserRepository> { UserRepositoryImpl(get()) }
}