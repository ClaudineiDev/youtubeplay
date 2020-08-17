package com.claudinei.youtubeplay.presentation.list_videos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.claudinei.youtubeplay.R
import com.claudinei.youtubeplay.data.model.Video
import kotlinx.android.synthetic.main.item_video.view.*

class VideosAdapter(
    private val videos: List<Video>,
    val onItemClickListener: ((video: Video) -> Unit)
): RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): VideosViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
            return VideosViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount() = videos.count()

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bindView(videos[position])
    }
    class VideosViewHolder (itemView: View, val onItemClickListener: ((video: Video) -> Unit)): RecyclerView.ViewHolder(itemView){
        private val title = itemView.item_title
        private val channel = itemView.item_channel
        private val description = itemView.item_desc
        fun bindView(video: Video){
            title.text = video.title
            channel.text = video.channelTitle
            description.text = video.description

            itemView.setOnClickListener {
                onItemClickListener.invoke(video)
            }
        }
    }
}