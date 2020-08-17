package com.claudinei.youtubeplay.data.repository

import com.claudinei.youtubeplay.data.APIService
import com.claudinei.youtubeplay.data.VideosResults
import com.claudinei.youtubeplay.data.model.Video
import com.claudinei.youtubeplay.data.response.VideosBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideosApiDataSource : VideosRepository{
    override fun getVideos(videosResultCallback: (result: VideosResults) -> Unit) {
        APIService.service.getVideos().enqueue(object : Callback<VideosBodyResponse> {
            override fun onResponse(call: Call<VideosBodyResponse>, response: Response<VideosBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        val videos: MutableList<Video> = mutableListOf()

                        response.body()?.let { videoBodyResponse ->
                            for (result in videoBodyResponse.videoItems) {
                                val video = result.videoSnippet[0].getVideoModel()
                                videos.add(video)
                            }
                        }

                        videosResultCallback(VideosResults.Success(videos))
                    }
                    else ->  videosResultCallback(VideosResults.ApiError(response.code()))
                }
            }

            override fun onFailure(call: Call<VideosBodyResponse>, t: Throwable) {
                videosResultCallback(VideosResults.ServerError)
            }
        })
    }
}