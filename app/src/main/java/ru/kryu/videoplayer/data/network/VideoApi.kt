package ru.kryu.videoplayer.data.network

import retrofit2.http.GET
import ru.kryu.videoplayer.BuildConfig

const val API_KEY = BuildConfig.PIXABAY_API_KEY

interface VideoApi {
    @GET("api/metainfo/tv/28/video/")
    suspend fun getVideos(): VideoDto
}