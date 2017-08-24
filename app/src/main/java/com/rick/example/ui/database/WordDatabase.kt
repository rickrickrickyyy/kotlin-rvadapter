package com.rick.example.ui.database

import android.arch.persistence.room.*
import com.rick.example.ui.dataclass.Word
import io.reactivex.Flowable

/**
 * Created by rick on 22/8/2017.
 */
@Database(entities = arrayOf(Word::class), version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
}

@Dao
interface WordDao {

    @Query("SELECT * FROM word")
    fun findAllWord(): Flowable<List<Word>>

    @Insert
    fun insert(word: Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: List<Word>)
}