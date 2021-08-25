package com.example.rgc.opendotaktor.domain

import com.example.rgc.opendotaktor.local.LocalDataSource
import com.example.rgc.opendotaktor.server.RemoteDataSource
import io.ktor.client.*

class HeroStatsRepositoryImpl(private val client : HttpClient,
                              private val remoteDataSource : RemoteDataSource,
                              private val localDataSource : LocalDataSource) : HeroStatsRepository {

    companion object {
        const val MAX_ITEMS = 20
    }

    override suspend fun getHeroStats(page : Int): List<HeroStats> {
        val firstIndex = (page - 1) * MAX_ITEMS
        val lastIndex = page * MAX_ITEMS - 1
        if (localDataSource.size()) {
            val heroStatsRemote = remoteDataSource.getHeroStats((client))
            localDataSource.saveHeroStats(heroStatsRemote)
        }
        return localDataSource.getHeroStats(firstIndex, lastIndex)
    }
}

interface HeroStatsRepository {
    suspend fun getHeroStats(page : Int) : List<HeroStats>
}
