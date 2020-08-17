package com.claudinei.youtubeplay.data

import com.claudinei.youtubeplay.data.model.Video

sealed class VideosResults {
    class Success(val books: List<Video>) : VideosResults()
    class ApiError(val statusCode: Int) : VideosResults()
    object ServerError : VideosResults()
}
