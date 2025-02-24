package ru.kryu.videoplayer.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApi {
    @GET("videos/")
    suspend fun getVideos(
        @Query("key") apiKey: String
    ): VideoDto
}