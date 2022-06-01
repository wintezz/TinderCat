package com.alexpetrov.tinder.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CatRequest(
    val image_id: String,
    val sub_id: String,
    val value: Int
)