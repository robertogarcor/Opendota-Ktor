package com.example.rgc.opendotaktor.heroes

import com.example.rgc.opendotaktor.heroes.domain.HeroStatsRepository
import com.example.rgc.opendotaktor.heroes.domain.getHeroes
import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.routing.*
import org.koin.core.parameter.parametersOf
import org.koin.ktor.ext.inject

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.hero(testing: Boolean = false) {

    val client = HttpClient(Apache) {
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
        install(Logging) {
            //logger = null
            //level = LogLevel.BODY
        }
    }

    val repository : HeroStatsRepository by inject{ parametersOf(client)}

    routing {
        this.getHeroes(repository)
    }
}