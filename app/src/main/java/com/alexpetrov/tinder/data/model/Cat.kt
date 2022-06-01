package com.alexpetrov.tinder.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Cat(
    val created_at: String,
    val value: Int,
    val url: String
)