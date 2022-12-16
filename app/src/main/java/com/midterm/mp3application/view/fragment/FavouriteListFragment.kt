package com.midterm.mp3application.view.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.midterm.mp3application.R
import com.midterm.mp3application.adapter.SongAdapter
import com.midterm.mp3application.base.BaseFragment
import com.midterm.mp3application.data.KEY_SONG_ID
import com.midterm.mp3application.databinding.FragmentFavouriteListBinding
import com.midterm.mp3application.view.activity.DetailActivity
import com.midterm.mp3application.viewmodel.FavouriteListViewModel
import com.midterm.mp3application.viewmodel.FavouriteListViewModelFactory

class FavouriteListFragment :
    BaseFragment<FragmentFavouriteListBinding>(R.layout.fragment_favourite_list) {


    private val viewModel: FavouriteListViewModel by viewModels {
        FavouriteListViewModelFactory(requireContext())
    }
    private var adapter: SongAdapter? = null


    override fun initView() {
        adapter = SongAdapter { song ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(KEY_SONG_ID, song.id)
            startActivity(intent)
        }
        binding.rcvFavouriteList.adapter = adapter
        binding.rcvFavouriteList.layoutManager = LinearLayoutManager(this.context)
    }

    override fun initObserver() {
        viewModel.favouriteList.observe(viewLifecycleOwner) {
            adapter?.submitList(it.filter { song -> song.isFavourite })
        }
    }

    override fun initViewListener() {
    }

}