package com.midterm.mp3application.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.midterm.mp3application.data.DataSource

class SongViewModel(dataSource: DataSource): ViewModel() {

    val songsLiveData = dataSource.getSongsList()

}

class SongViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SongViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SongViewModel(DataSource.getDataSource(context.resources)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}