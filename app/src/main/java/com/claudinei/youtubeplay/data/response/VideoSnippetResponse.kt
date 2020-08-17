package com.claudinei.youtubeplay.data.response

import com.claudinei.youtubeplay.data.model.Video
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoSnippetResponse (
    @Json(name = "title")
    val title: String,
    @Json(name = "channelTitle")
    val channelTitle: String,
    @Json(name = "description")
    val description: String

){
    fun getVideoModel() = Video(
        title = this.title,
        channelTitle = this.channelTitle,
        description = this.description
    )
}
