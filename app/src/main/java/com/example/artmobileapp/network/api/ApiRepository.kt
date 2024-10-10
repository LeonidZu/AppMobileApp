package com.example.artmobileapp.network.api

import com.example.artmobileapp.models.SearchResponse
import com.example.artmobileapp.network.api.ApiClient.client
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiRepository {
    suspend fun getImages(): SearchResponse = client.get(ApiRoutes.SEARCH).body()
}