package com.midterm.mp3application.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.midterm.mp3application.view.fragment.LyricsDetailFragment
import com.midterm.mp3application.view.fragment.MainDetailFragment

class DetailViewPagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MainDetailFragment()
            1 -> LyricsDetailFragment()
            else -> MainDetailFragment()
        }
    }
}