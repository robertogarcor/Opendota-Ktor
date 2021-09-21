package com.example.rgc.opendotaktor

import com.example.rgc.opendotaktor.auth.dikoin.authModule
import com.example.rgc.opendotaktor.heroes.dikoin.heroesModule
import com.example.rgc.opendotaktor.users.dikoin.userModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import org.koin.ktor.ext.Koin

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.main(testing: Boolean = false) {
    // Declare install gson
    install(ContentNegotiation) {
        gson {  }
    }
    // Declare install Koin modules
    install(Koin) {
        modules(heroesModule, authModule, userModule)
    }

    databaseFactoryInit()

}







