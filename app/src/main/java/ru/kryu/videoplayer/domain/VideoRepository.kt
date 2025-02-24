package ru.kryu.videoplayer.domain

import ru.kryu.videoplayer.domain.model.Video

interface VideoRepository {
    suspend fun getVideos(): List<Video>
}