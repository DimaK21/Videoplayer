package ru.kryu.videoplayer.data.mapper

import ru.kryu.videoplayer.data.database.VideoEntity
import ru.kryu.videoplayer.data.network.VideoDto
import ru.kryu.videoplayer.domain.model.Video

fun VideoDto.toEntity() = this.hits.map {
    VideoEntity(
        id = it.id.toString(),
        title = it.tags,
        thumbnailUrl = it.videos?.medium?.thumbnail ?: "",
        videoUrl = it.videos?.medium?.url ?: "",
        duration = it.duration ?: 0,
    )
}

fun VideoEntity.toDomain() = Video(
    id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    videoUrl = videoUrl,
    duration = duration,
)
