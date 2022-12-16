package com.midterm.mp3application.data

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes


data class Song(
    val id: Int,
    val songName: String,
    val singleName: String,
    @RawRes
    val songSrc: Int?,
    @DrawableRes
    val imageSrc: Int?,
    @StringRes
    val lyrics: Int,
    var isDelete: Boolean = false,
    var isFavourite: Boolean = false
): java.io.Serializable