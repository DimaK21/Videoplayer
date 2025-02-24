package ru.kryu.videoplayer.data.mapper

import ru.kryu.videoplayer.data.database.VideoEntity
import ru.kryu.videoplayer.data.network.VideoDto
import ru.kryu.videoplayer.domain.model.Video

fun VideoDto.toEntity() = this.results?.map {
    VideoEntity(
        id = it?.id ?: "",
        title = it?.title ?: "",
        thumbnailUrl = it?.thumbnailUrl ?: "",
        videoUrl = it?.videoUrl ?: "",
        duration = it?.duration ?: 0,
    )
} ?: emptyList()

fun VideoEntity.toDomain() = Video(
    id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    videoUrl = videoUrl,
    duration = duration,
)

fun VideoDto.toDomain() = this.results?.map {
    Video(
        id = it?.id ?: "",
        title = it?.title ?: "",
        thumbnailUrl = it?.thumbnailUrl ?: "",
        videoUrl = it?.videoUrl ?: "",
        duration = it?.duration ?: 0,
    )
} ?: emptyList()
