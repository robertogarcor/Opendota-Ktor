package com.example.rgc.opendotaktor.heroes.local

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

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

object Herostats : Table("herostats") {
    val heroId: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 100)
    val localizedName: Column<String> = varchar("localizedName", 100)
    val primaryAttr: Column<String> = varchar("primaryAttr", 100)
    val attackType: Column<String> = varchar("attackType", 100)
    val image: Column<String> = varchar("image", 250)
    override val primaryKey = PrimaryKey(heroId, name = "pk_hero")
}
