package com.claudinei.youtubeplay.data

import com.claudinei.youtubeplay.data.response.VideosBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface YTServices {

    @GET("search")
    fun getVideos(
        @Query("part") part: String = "snippet",
        @Query("channelId") channelId: String = "UC08IZDh4NSFHl8KYpYNdoVg",
        @Query("key") key: String = "AIzaSyB7KIw7zxnV8z_un4EVnSmIEELPbmT34mQ"
    ): Call<VideosBodyResponse>
}