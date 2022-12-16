package com.midterm.mp3application.view.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.midterm.mp3application.R
import com.midterm.mp3application.base.BaseFragment
import com.midterm.mp3application.data.KEY_SONG_ID
import com.midterm.mp3application.data.Song
import com.midterm.mp3application.databinding.FragmentLyricsDetailBinding
import com.midterm.mp3application.viewmodel.DetailViewModel
import com.midterm.mp3application.viewmodel.DetailViewModelFactory

class LyricsDetailFragment :
    BaseFragment<FragmentLyricsDetailBinding>(R.layout.fragment_lyrics_detail) {

    private var extras: Bundle? = null
    private var selectedSong: Song? = null

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(requireContext())
    }

    override fun initView() {
        extras = activity?.intent?.extras
        selectedSong = viewModel.getSongById(extras?.get(KEY_SONG_ID).toString().toInt())

        binding.txtSongLyrics.text = selectedSong?.lyrics?.let { getString(it) }
    }

    override fun initObserver() {

    }

    override fun initViewListener() {

    }


}