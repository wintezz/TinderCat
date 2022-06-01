package com.alexpetrov.tinder.data.ktor

import com.alexpetrov.tinder.data.dto.CatRequest
import com.alexpetrov.tinder.data.dto.CatResponce
import com.alexpetrov.tinder.data.dto.Image
import com.alexpetrov.tinder.data.dto.Message
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class CatServiceImpl(
    private val client: HttpClient
) : CatService {
    override suspend fun getImage(): List<Image> {
        return client.get {
            url(Resource.imageUrl)
        }
    }

    override suspend fun getImageById(id: String): Image {
        val url = "$baseUrl$id?$apiKey"
        return client.get {
            url(url)
        }
    }

    override suspend fun getCatList(): List<CatResponce> {
        return client.get {
            url(Resource.catUrl)
        }
    }

    override suspend fun createCat(postRequest: CatRequest): Message {
        return client.post {
            url(Resource.catUrl)
            contentType(ContentType.Application.Json)
            body = postRequest
        }
    }

    companion object {
        private const val baseUrl = "https://api.thecatapi.com/v1/images/"
        private const val apiKey = "api_key=b9940f18-3124-4b46-bb7d-2f03a64e11e2"
    }
}