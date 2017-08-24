package com.rick.example

import android.app.Application
import android.arch.persistence.room.Room
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.GsonBuilder
import com.rick.example.ui.database.MyDatabase
import com.rick.example.ui.dataclass.Word
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

/**
 * Created by rick on 22/8/2017.
 */
class MyApp : Application() {

    companion object {
        var database: MyDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        MyApp.database = Room.databaseBuilder(this, MyDatabase::class.java, "we-need-db").build()


        loadRawWords().subscribe {}
    }

    fun loadRawWords(): Completable =
            Completable.fromAction(
                    {
                        val use: String = resources.openRawResource(R.raw.words).bufferedReader().use { it.readText() }
                        val fromJson = GsonBuilder().create().fromJson<List<Word>>(use)
                        MyApp.database?.wordDao()?.insert(fromJson)
                    }
            ).subscribeOn(Schedulers.io())

}