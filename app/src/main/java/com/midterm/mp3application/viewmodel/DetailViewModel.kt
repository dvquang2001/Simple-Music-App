package com.midterm.mp3application.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.midterm.mp3application.data.DataSource
import com.midterm.mp3application.data.Song

class DetailViewModel(private val dataSource: DataSource): ViewModel() {

    fun getSongById(id: Int): Song? = dataSource.getSongById(id)

    private var _currentTime = MutableLiveData(0)
    val currentTime get() = _currentTime

    private var _totalTime = MutableLiveData(0)
    val totalTime get() = _totalTime

}


class DetailViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(DataSource.getDataSource(context.resources)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}