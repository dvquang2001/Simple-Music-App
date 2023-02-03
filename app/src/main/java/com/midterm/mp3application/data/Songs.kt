package com.midterm.mp3application.data

import android.content.res.Resources
import com.midterm.mp3application.R

fun songList(resources: Resources): List<Song> =
    listOf(
        Song(
            1,
            "Happy new year",
            "ABBA",
            R.raw.happynewyear,
            R.drawable.abba,
            R.string.happy_new_year_lyrics
        ),
        Song(
            2,
            "Merry Christmas",
            "Feliz",
            R.raw.merry_christmas,
            R.drawable.feliz,
            R.string.happy_new_year_lyrics
        ),
        Song(
            3,
            "Fly",
            "Fransis Derelle",
            R.raw.fly,
            R.drawable.ncs,
            R.string.fly_lyrics
        ),
        Song(
            4,
            "See you again",
            "Charlie Puth",
            R.raw.see_you_again,
            R.drawable.charlieputh,
            R.string.see_you_again_lyrics,
            isFavourite = true
        ),
        Song(
            5,
            "Dreamers",
            "World cup 2022",
            R.raw.dreamers_wc,
            R.drawable.dreamers_wc,
            R.string.dreamers_lyrics,
            isFavourite = true
        ),
        Song(
            6,
            "Waka waka",
            "Shakira",
            R.raw.waka_waka,
            R.drawable.shakira,
            R.string.waka_waka_lyrics,
            isDelete = true
        )

    )
