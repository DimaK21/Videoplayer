package ru.kryu.videoplayer.data.network

import retrofit2.http.GET

interface VideoApi {
    @GET("api/metainfo/tv/28/video/")
    suspend fun getVideos(): VideoDto
}