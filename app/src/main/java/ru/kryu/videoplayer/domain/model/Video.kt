package ru.kryu.videoplayer.domain.model

data class Video(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val duration: Int,
)
