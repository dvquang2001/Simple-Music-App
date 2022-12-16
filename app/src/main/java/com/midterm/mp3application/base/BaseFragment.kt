package com.midterm.mp3application.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<BINDING : ViewDataBinding>(val layoutId: Int) : Fragment(),
    View.OnClickListener {
    protected lateinit var binding: BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        initViewListener()
    }

    override fun onClick(view: View?) {
        view?.let { onViewClicked() }
    }

    open fun onViewClicked() {}

    abstract fun initView()
    abstract fun initObserver()
    abstract fun initViewListener()

}