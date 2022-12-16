package com.midterm.mp3application.view.fragment

import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import com.midterm.mp3application.R
import com.midterm.mp3application.base.BaseFragment
import com.midterm.mp3application.databinding.FragmentMainDetailBinding
import com.midterm.mp3application.viewmodel.DetailViewModel
import com.midterm.mp3application.viewmodel.DetailViewModelFactory

class MainDetailFragment : BaseFragment<FragmentMainDetailBinding>(R.layout.fragment_main_detail) {


    private val viewModel: DetailViewModel by activityViewModels {
        DetailViewModelFactory(requireContext())
    }

    override fun initView() {
        binding.imgDisc.animation = AnimationUtils.loadAnimation(context, R.anim.rotate_animation)
    }

    override fun initObserver() {
    }

    override fun initViewListener() {
    }

}