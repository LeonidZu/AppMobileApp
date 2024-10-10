package com.example.artmobileapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val id: String,
    val dimension_x: String,
    val dimension_y: String,
    val ratio: Float,
    val path: String,
    val thumbs: ImageThumb
)

@Serializable
data class SearchResponse(
    val data: List<Image>
)

@Serializable
data class ImageThumb(
    val large: String,
    val original: String,
    val small: String
)
