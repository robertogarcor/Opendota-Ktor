package com.example.rgc.opendotaktor.utils

import com.example.rgc.opendotaktor.heroes.domain.HeroStats
import com.example.rgc.opendotaktor.heroes.local.HeroStatsLocal
import com.example.rgc.opendotaktor.heroes.local.Herostats
import org.jetbrains.exposed.sql.ResultRow
import com.example.rgc.opendotaktor.heroes.server.HeroStatsServer as HeroStatsServer

class EntityMapperImpl : EntityMapper<HeroStatsServer, HeroStats, HeroStatsLocal> {

    override fun mapFromEntityLocal(entity: HeroStatsServer): HeroStatsLocal {
        return HeroStatsLocal(
            id = entity.id,
            name = entity.name,
            localizedName = entity.localized_name,
            primaryAttr =  entity.primary_attr,
            attackType = entity.attack_type,
            image  = entity.img
        )
    }

    override fun mapToDomainModel(localModel: HeroStatsLocal): HeroStats {
        return HeroStats(
            id = localModel.id,
            name = localModel.name,
            localizedName = localModel.localizedName,
            primaryAttr =  localModel.primaryAttr,
            attackType = localModel.attackType,
            img  = localModel.image
        )
    }

    override fun mapToLocalModel(dbModel: ResultRow): HeroStatsLocal {
        return HeroStatsLocal(
            id = dbModel[Herostats.id],
            name = dbModel[Herostats.name],
            localizedName = dbModel[Herostats.localizedName],
            primaryAttr =  dbModel[Herostats.primaryAttr],
            attackType = dbModel[Herostats.attackType],
            image  = dbModel[Herostats.image]
        )
    }

    override fun mapFromEntityList(entities: List<HeroStatsServer>) : List<HeroStatsLocal> {
        return entities.map { mapFromEntityLocal(it)}
    }

    override fun mapToLocalList(entities: List<HeroStatsLocal>): List<HeroStats> {
        return entities.map { mapToDomainModel(it) }
    }


}