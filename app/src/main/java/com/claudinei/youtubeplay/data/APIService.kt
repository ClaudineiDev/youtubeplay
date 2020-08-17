package com.claudinei.youtubeplay.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object APIService {
    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    val  service: YTServices = initRetrofit().create(YTServices::class.java)
}