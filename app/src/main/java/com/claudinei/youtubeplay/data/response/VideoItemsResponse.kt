package com.claudinei.youtubeplay.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoItemsResponse (
    @Json(name = "snippet")
    val videoSnippet: List<VideoSnippetResponse>
)
