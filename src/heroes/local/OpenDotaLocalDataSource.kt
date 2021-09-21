package com.example.rgc.opendotaktor.heroes.local

import com.example.rgc.opendotaktor.dbQuery
import com.example.rgc.opendotaktor.heroes.domain.HeroStats
import com.example.rgc.opendotaktor.heroes.mapping.EntityMapperImpl
import org.jetbrains.exposed.sql.*

class OpenDotaLocalDataSource(private val entityMapper : EntityMapperImpl) : LocalDataSource {

    companion object {
        val URL_BASE_IMAGE = "https://api.opendota.com"
    }

    override suspend fun getHeroStats(maxItems : Int, firstIndex : Long): List<HeroStats> = dbQuery {
        Herostats.selectAll()
            .limit(maxItems,firstIndex)
            .map { heroesDb -> entityMapper.mapToLocalModel(heroesDb) }
            .map { heroesDomain -> entityMapper.mapToDomainModel(heroesDomain) }
    }

    override suspend fun saveHeroStats(heroStatsList: List<HeroStatsLocal>) : Unit = dbQuery {
        Herostats.batchInsert(heroStatsList) { hero ->
            this[Herostats.name] = hero.name
            this[Herostats.localizedName] = hero.localizedName
            this[Herostats.primaryAttr] = hero.primaryAttr
            this[Herostats.attackType] = hero.attackType
            this[Herostats.image] = URL_BASE_IMAGE+"${hero.image}"
        }
    }

    override suspend fun isEmpty() : Boolean = dbQuery {
        Herostats.selectAll().empty()
    }
}


interface LocalDataSource {
    suspend fun getHeroStats(maxItems : Int, firstIndex : Long) : List<HeroStats>
    suspend fun saveHeroStats(heroStatsList : List<HeroStatsLocal>)
    suspend fun isEmpty() : Boolean
}