package com.example.rgc.opendotaktor.server

import com.example.rgc.opendotaktor.local.HeroStatsLocal
import com.example.rgc.opendotaktor.utils.EntityMapperImpl
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class OpenDotaRemoteDataSource() : RemoteDataSource {

    private val entityMapper = EntityMapperImpl()

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