package com.example.rgc.opendotaktor.heroes.dikoin

import com.example.rgc.opendotaktor.heroes.domain.HeroStatsRepository
import com.example.rgc.opendotaktor.heroes.domain.HeroStatsRepositoryImpl
import com.example.rgc.opendotaktor.heroes.local.LocalDataSource
import com.example.rgc.opendotaktor.heroes.local.OpenDotaLocalDataSource
import com.example.rgc.opendotaktor.heroes.server.OpenDotaRemoteDataSource
import com.example.rgc.opendotaktor.heroes.server.RemoteDataSource
import com.example.rgc.opendotaktor.heroes.mapping.EntityMapperImpl
import io.ktor.client.*
import org.koin.dsl.module

val heroesModule = module {
    single { EntityMapperImpl() }
    single<RemoteDataSource> {
        OpenDotaRemoteDataSource(get())
    }
    single<LocalDataSource> {
        OpenDotaLocalDataSource(get())
    }
    single<HeroStatsRepository> { (client : HttpClient) ->
        HeroStatsRepositoryImpl(client, get(), get())
    }
}
