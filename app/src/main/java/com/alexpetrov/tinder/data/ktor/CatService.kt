package com.alexpetrov.tinder.data.ktor

import com.alexpetrov.tinder.data.dto.CatRequest
import com.alexpetrov.tinder.data.dto.CatResponce
import com.alexpetrov.tinder.data.dto.Image
import com.alexpetrov.tinder.data.dto.Message
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface CatService {

    suspend fun getImage(): List<Image>
    suspend fun getImageById(id: String): Image
    suspend fun createCat(postRequest: CatRequest): Message
    suspend fun getCatList(): List<CatResponce>

    companion object {
        fun create(): CatService {
            return CatServiceImpl(
                HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json() {
                            ignoreUnknownKeys = true
                            isLenient = true
                        })
                    }
                }
            )
        }
    }
}
