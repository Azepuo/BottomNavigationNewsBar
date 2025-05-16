package com.example.bottomnavigationnewsbar.network

import com.example.bottomnavigationnewsbar.models.NewsResponse

import retrofit2.Response // Important: utiliser retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// Placer cette constante au niveau supérieur du fichier ou dans un objet companion
private const val API_KEY = "92574e6f0ff0492f845a1387581c6b88"

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse> // Utiliser Response<T> pour gérer les erreurs HTTP

    @GET("v2/everything")
    suspend fun getSearchNews(
        @Query("q") searchQuery: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse> //import retrofit2.Response
}