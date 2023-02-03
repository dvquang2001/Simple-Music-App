package com.midterm.mp3application.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.midterm.mp3application.data.Song
import com.midterm.mp3application.databinding.ActivityDetailBinding
import com.midterm.mp3application.other.Constant.KEY_SELECTED_SONG
import com.midterm.mp3application.other.Constant.KEY_SONG_ID
import com.midterm.mp3application.service.SongService
import com.midterm.mp3application.view.adapter.DetailViewPagerAdapter
import com.midterm.mp3application.viewmodel.DetailViewModel
import com.midterm.mp3application.viewmodel.DetailViewModelFactory

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!
    private var extras: Bundle? = null
    private var selectedSong: Song? = null
    private var soundService: SongService? = null

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        extras = intent?.extras
        selectedSong = viewModel.getSongById(extras?.get(KEY_SONG_ID).toString().toInt())

        startServiceIntent()
        setUpViewPager()
        binding.txtSongName.text = selectedSong?.songName
        binding.txtSingleName.text = selectedSong?.singleName
        soundService = SongService()
    }

    private fun startServiceIntent() {
        val serviceIntent = Intent(this, SongService::class.java)
        val bundle = Bundle()
        bundle.putSerializable(KEY_SELECTED_SONG, selectedSong)
        serviceIntent.putExtras(bundle)
        startService(serviceIntent)
    }

    private fun setUpViewPager() {
        val viewPagerAdapter = DetailViewPagerAdapter(this)
        binding.detailViewPager.adapter = viewPagerAdapter
        binding.indicator.setViewPager(binding.detailViewPager)
    }
}