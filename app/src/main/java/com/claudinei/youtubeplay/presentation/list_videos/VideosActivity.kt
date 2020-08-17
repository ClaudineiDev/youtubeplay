package com.claudinei.youtubeplay.presentation.list_videos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets.of
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStores.of
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.claudinei.youtubeplay.R
import com.claudinei.youtubeplay.data.model.Video
import com.claudinei.youtubeplay.data.repository.VideosApiDataSource
import com.claudinei.youtubeplay.data.repository.VideosRepository
import com.claudinei.youtubeplay.presentation.details.VideoDetailsActivity
import kotlinx.android.synthetic.main.activity_videos.*

class VideosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)

        toobarMain.title = getString(R.string.videos_title)
        setSupportActionBar(toobarMain)
        val viewModel: VideosViewModel = VideosViewModel.ViewModelFactory(VideosApiDataSource())
            .create(VideosViewModel::class.java)
        //val viewModel: VideosViewModel = ViewModelProviders.of(this).get(VideosViewModel::class.java)
        viewModel.videosLiveData.observe(this, Observer {

            it?.let {videos ->
                with(recycler_videos) {
                    layoutManager = LinearLayoutManager(this@VideosActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = VideosAdapter(videos){ video ->
                        val intent = VideoDetailsActivity.getStartIntent(this@VideosActivity, video.title, video.description)
                        this@VideosActivity.startActivity(intent)
                    }
                }
            }
        })
        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperVideos.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })
        viewModel.getVideos()

    }


}