package ru.kryu.videoplayer.data.network

import com.google.gson.annotations.SerializedName

data class VideoDto(
    @SerializedName("has_next")
    val hasNext: Boolean?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("results")
    val results: List<Result?>?
) {
    data class Result(
        @SerializedName("id")
        val id: String,
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?,
        @SerializedName("video_url")
        val videoUrl: String?,
        @SerializedName("duration")
        val duration: Int?,
        @SerializedName("title")
        val title: String?,
    )
}
