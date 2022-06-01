package com.alexpetrov.tinder.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CatResponce(
    val image_id: String,
    val created_at: String,
    val value: Int
)