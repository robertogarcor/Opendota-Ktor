package com.example.rgc.opendotaktor.heroes.domain

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.heroes(repository : HeroStatsRepository) {
    authenticate("auth-jwt") {
        get("/heroStats") {
            val paramPage: String? = call.request.queryParameters["page"]
            val page: String = if (paramPage === null) "0" else paramPage
            call.respond(repository.getHeroStats(Integer.parseInt(page)))
        }
    }
}



