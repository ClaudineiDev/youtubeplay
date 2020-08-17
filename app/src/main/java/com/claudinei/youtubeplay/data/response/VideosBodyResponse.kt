package com.claudinei.youtubeplay.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideosBodyResponse (
    @Json(name = "items")
    val videoItems: List<VideoItemsResponse>
)