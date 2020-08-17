package com.claudinei.youtubeplay.presentation.list_videos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.claudinei.youtubeplay.R
import com.claudinei.youtubeplay.data.VideosResults
import com.claudinei.youtubeplay.data.model.Video
import com.claudinei.youtubeplay.data.repository.VideosRepository

class VideosViewModel(private val dataSource: VideosRepository) : ViewModel() {

    val videosLiveData: MutableLiveData<List<Video>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getVideos() {
        dataSource.getVideos { result: VideosResults ->
            when (result) {
                is VideosResults.Success -> {
                    videosLiveData.value = result.books
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_VIDEOS, null)
                }
                is VideosResults.ApiError -> {
                    if (result.statusCode == 401) {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.videos_error_401)
                    } else {
                        viewFlipperLiveData.value =
                            Pair(VIEW_FLIPPER_ERROR, R.string.videos_error_400_generic)
                    }
                }
                is VideosResults.ServerError -> {
                    viewFlipperLiveData.value =
                        Pair(VIEW_FLIPPER_ERROR, R.string.videos_error_500_generic)
                }
            }
        }
    }

    class ViewModelFactory(private val dataSource: VideosRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VideosViewModel::class.java)) {
                return VideosViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_VIDEOS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}