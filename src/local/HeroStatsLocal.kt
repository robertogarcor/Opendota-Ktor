package com.example.rgc.opendotaktor.local

data class HeroStatsLocal(
    val id: Int,
    val name: String,
    val localizedName: String,
    val primaryAttr: String,
    val attackType: String,
    val image : String
    ) {

    companion object {
        var heroStats : List<HeroStatsLocal> = ArrayList()
    }

}
