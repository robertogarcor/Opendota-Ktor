package com.example.rgc.opendotaktor.heroes.domain

import com.example.rgc.opendotaktor.heroes.local.LocalDataSource
import com.example.rgc.opendotaktor.heroes.server.RemoteDataSource
import io.ktor.client.*

class HeroStatsRepositoryImpl(private val client : HttpClient,
                              private val remoteDataSource : RemoteDataSource,
                              private val localDataSource : LocalDataSource) : HeroStatsRepository {

    companion object {
        const val MAX_ITEMS = 20
    }

    override suspend fun getHeroStats(page : Int): List<HeroStats> {
        val firstIndex : Long = ((page - 1) * MAX_ITEMS).toLong()
        val maxItems = MAX_ITEMS
        if (localDataSource.isEmpty()) {
            val heroStatsRemote = remoteDataSource.getHeroStats((client))
            localDataSource.saveHeroStats(heroStatsRemote)
        }
        return localDataSource.getHeroStats(maxItems, firstIndex)
    }
}

interface HeroStatsRepository {
    suspend fun getHeroStats(page : Int) : List<HeroStats>
}
