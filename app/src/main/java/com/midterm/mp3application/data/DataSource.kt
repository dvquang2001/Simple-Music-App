package com.midterm.mp3application.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(private val resources: Resources) {

    private var initSongList = songList(resources)
    private val songsLiveData = MutableLiveData(initSongList)

    fun deleteSong(song: Song) {
        val currentList = songsLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            song.isDelete = true
            updatedList.filter { it.isDelete }
            songsLiveData.postValue(updatedList)
        }
    }

    fun getSongsList(): LiveData<List<Song>> {
        return songsLiveData
    }

    fun getSongById(id: Int): Song? {
        songsLiveData.value?.let { songs ->
            return songs.firstOrNull { it.id == id }
        }
        return null
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }

}