package com.example.rgc.opendotaktor.heroes.server

import com.example.rgc.opendotaktor.heroes.local.HeroStatsLocal
import com.example.rgc.opendotaktor.heroes.mapping.EntityMapperImpl
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class OpenDotaRemoteDataSource(private val entityMapper : EntityMapperImpl) : RemoteDataSource {

    companion object {
        val URL_BASE : String = "https://api.opendota.com/api/heroStats"
    }

    override suspend fun getHeroStats(client : HttpClient): List<HeroStatsLocal> {
        return client.get<List<HeroStatsServer>> {
            url(URL_BASE)
            contentType(ContentType.Application.Json)
        }.map { entityMapper.mapFromEntityLocal(it) }

    }
}


interface RemoteDataSource {
    suspend fun getHeroStats(client : HttpClient) : List<HeroStatsLocal>
}