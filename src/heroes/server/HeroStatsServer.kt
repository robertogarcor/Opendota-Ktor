package com.example.rgc.opendotaktor.heroes.server

data class HeroStatsServer(
    val id: Int,
    val name: String,
    val localized_name: String,
    val primary_attr: String,
    val attack_type: String,
    val img : String
    )
