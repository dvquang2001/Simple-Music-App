package com.midterm.mp3application.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.midterm.mp3application.view.fragment.DeletedListFragment
import com.midterm.mp3application.view.fragment.FavouriteListFragment
import com.midterm.mp3application.view.fragment.SongListFragment

class MyViewPagerAdapter(private val fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SongListFragment()
            1 -> FavouriteListFragment()
            2 -> DeletedListFragment()
            else -> SongListFragment()
        }
    }
}