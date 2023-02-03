package com.midterm.mp3application.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.midterm.mp3application.data.Song
import com.midterm.mp3application.databinding.ItemLayoutBinding

class SongAdapter( private val onItemClicked: (Song) -> Unit): ListAdapter<Song, SongAdapter.SongViewHolder>(
    DiffCallback
) {

    class SongViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.apply {
                txtName.text = song.songName
                imageView.setImageResource(song.imageSrc!!)
            }
        }
    }

    companion object {
        private val DiffCallback = object: DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val currentSong = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(currentSong)
        }
        holder.bind(currentSong)
    }
}