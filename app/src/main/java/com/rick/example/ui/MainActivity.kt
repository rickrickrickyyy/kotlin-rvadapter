package com.rick.example.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.rick.example.MyApp
import com.rick.example.R
import com.rick.example.ui.binding.MainActivityAdapter
import com.rick.example.ui.dataclass.Word
import com.rick.onetrueadapter.LayoutsAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var data = MutableList<Any>(0, { _ -> Word() })

    lateinit var adapter: LayoutsAdapter<Any>


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                data.add(110)
                adapter.notifyDataSetChanged()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                data.add("add string")
                adapter.notifyDataSetChanged()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                data.add(false)
                adapter.notifyDataSetChanged()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insertWords()
        adapter = MainActivityAdapter(baseContext, data)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun insertWords() {
        MyApp.database?.wordDao()?.findAllWord()
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ t: List<Word> ->
                    data.clear()
                    data.addAll(t)
                    adapter.notifyDataSetChanged()
                })
    }
}
