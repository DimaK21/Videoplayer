package ru.kryu.videoplayer.data.network

import com.google.gson.annotations.SerializedName

data class VideoDto(
    @SerializedName("hits")
    val hits: List<Hit>
) {
    data class Hit(
        @SerializedName("id")
        val id: Int,
        @SerializedName("tags")
        val tags: String,
        @SerializedName("duration")
        val duration: Int?,
        @SerializedName("videos")
        val videos: Videos?,
    ) {
        data class Videos(
            @SerializedName("medium")
            val medium: Medium?,
        ) {
            data class Medium(
                @SerializedName("url")
                val url: String?,
                @SerializedName("width")
                val width: Int?,
                @SerializedName("height")
                val height: Int?,
                @SerializedName("size")
                val size: Int?,
                @SerializedName("thumbnail")
                val thumbnail: String?
            )
        }
    }
}
