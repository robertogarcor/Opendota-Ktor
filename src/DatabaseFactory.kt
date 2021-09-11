package com.example.rgc.opendotaktor

import com.example.rgc.opendotaktor.heroes.local.HeroStats
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

const val HIKARI_CONFIG_KEY = "ktor.db.hikariConfig"


fun Application.databaseFactoryInit() {

    val configPath = environment.config.property(HIKARI_CONFIG_KEY).getString()
    val dbConfig = HikariConfig(configPath)
    val dataSource = HikariDataSource(dbConfig)
    Database.connect(dataSource)
    createTables()
    }

    private fun createTables() {
        transaction {
            SchemaUtils.create(HeroStats)
    }

}