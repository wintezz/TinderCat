package com.alexpetrov.tinder.data.ktor

object Resource {

    private const val apiKey = "b9940f18-3124-4b46-bb7d-2f03a64e11e2"
    private const val baseUrl = "https://api.thecatapi.com"

    const val imageUrl = "$baseUrl/v1/images/search?api_key=$apiKey"
    const val catUrl = "$baseUrl/v1/votes?api_key=$apiKey"
}