package com.midterm.mp3application.view.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.midterm.mp3application.R
import com.midterm.mp3application.adapter.SongAdapter
import com.midterm.mp3application.base.BaseFragment
import com.midterm.mp3application.data.KEY_SONG_ID
import com.midterm.mp3application.databinding.FragmentSongListBinding
import com.midterm.mp3application.service.SoundService
import com.midterm.mp3application.view.activity.DetailActivity
import com.midterm.mp3application.viewmodel.SongViewModel
import com.midterm.mp3application.viewmodel.SongViewModelFactory

class SongListFragment : BaseFragment<FragmentSongListBinding>(R.layout.fragment_song_list) {

    private val viewModel: SongViewModel by viewModels {
        SongViewModelFactory(requireContext())
    }
    private var adapter: SongAdapter? = null

    override fun initView() {
        adapter = SongAdapter { song ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(KEY_SONG_ID, song.id)
            startActivity(intent)
        }
        binding.rcvSongList.adapter = adapter
        binding.rcvSongList.layoutManager = LinearLayoutManager(this.context)
    }

    override fun initObserver() {
        viewModel.songsLiveData.observe(viewLifecycleOwner) { songs ->
            adapter?.submitList(songs)
        }
    }

    override fun initViewListener() {
    }

    override fun onDestroy() {
        activity?.stopService(Intent(activity, SoundService::class.java))
        super.onDestroy()
    }

}