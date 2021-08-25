package com.example.rgc.opendotaktor.local

import com.example.rgc.opendotaktor.domain.HeroStats
import com.example.rgc.opendotaktor.utils.EntityMapperImpl

class OpenDotaLocalDataSource : LocalDataSource {

    private val entityMapper = EntityMapperImpl()

    override suspend fun getHeroStats(firstIndex : Int, lastIndex : Int): List<HeroStats> {
        return HeroStatsLocal.heroStats.filterIndexed { index, heroStatsLocal ->
            index in firstIndex..lastIndex
        }.map { heroes -> entityMapper.mapToDomainModel(heroes) }
    }

    override suspend fun saveHeroStats(heroStatsList: List<HeroStatsLocal>) {
        HeroStatsLocal.heroStats = heroStatsList
    }

    override fun isEmpty(): Boolean {
        return HeroStatsLocal.heroStats.isEmpty()
    }
}


interface LocalDataSource {
    suspend fun getHeroStats(firstIndex : Int, lastIndex : Int) : List<HeroStats>
    suspend fun saveHeroStats(heroStatsList : List<HeroStatsLocal>)
    fun isEmpty() : Boolean
}