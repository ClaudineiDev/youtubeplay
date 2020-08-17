package com.claudinei.youtubeplay.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.claudinei.youtubeplay.R
import kotlinx.android.synthetic.main.activity_video_details.*

class VideoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_details)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val description = intent.getStringExtra(EXTRA_DESC)

        detail_title.text = title
        detail_desc.text = description
    }
    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESC = "EXTRA_DESC"

        fun getStartIntent(context: Context, title: String, description: String): Intent{
            return Intent(context, VideoDetailsActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_DESC, description)
            }
        }
    }
}