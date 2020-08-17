package com.claudinei.youtubeplay.data.repository

import com.claudinei.youtubeplay.data.VideosResults

interface VideosRepository {

    fun getVideos(videosResultCallback: (result: VideosResults) -> Unit)
}