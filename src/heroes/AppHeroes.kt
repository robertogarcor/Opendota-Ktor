package com.example.rgc.opendotaktor.heroes

import com.example.rgc.opendotaktor.domain.HeroStatsRepository
import com.example.rgc.opendotaktor.domain.HeroStatsRepositoryImpl
import com.example.rgc.opendotaktor.domain.getHeroes
import com.example.rgc.opendotaktor.local.OpenDotaLocalDataSource
import com.example.rgc.opendotaktor.server.OpenDotaRemoteDataSource
import com.example.rgc.opendotaktor.utils.EntityMapperImpl
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.hero(testing: Boolean = false) {
    install(ContentNegotiation) {
        gson {
        }
    }

    val client = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Logging) {
            //logger = null
            //level = LogLevel.BODY
        }
    }

    val repository: HeroStatsRepository = HeroStatsRepositoryImpl(client,
        OpenDotaRemoteDataSource(EntityMapperImpl()),
        OpenDotaLocalDataSource(EntityMapperImpl())
    )
    routing {
        this.getHeroes(repository)
    }
}