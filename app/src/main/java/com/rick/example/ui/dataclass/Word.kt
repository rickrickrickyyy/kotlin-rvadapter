package com.rick.example.ui.dataclass

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by rick on 22/8/2017.
 */
@Entity
data class Word(
        @PrimaryKey(autoGenerate = false)
        var word: String = "",
        var count: Int = 0,
        var type: String = "",
        var definition: String = ""
)