package com.example.rgc.opendotaktor

import com.example.rgc.opendotaktor.domain.HeroStats
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlin.test.*
import io.ktor.server.testing.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ hero(testing = true) }) {
            handleRequest(HttpMethod.Get, "/heroStats?page=0").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                //assertEquals("", response.content)
                assertTrue { ArrayList<HeroStats>().isEmpty() }
            }
        }
    }
}
