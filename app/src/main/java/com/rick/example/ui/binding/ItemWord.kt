package com.rick.example.ui.binding

import android.view.View
import com.rick.example.ui.dataclass.Word
import kotlinx.android.synthetic.main.item_word.view.*

/**
 * Created by rick on 24/8/2017.
 */
fun intItemWord(): (Int, View) -> Unit = {
    i, v: View ->
    v.text.text = i.toString()
}

fun wordItemWord(): (Word, View) -> Unit = {
    (word), v: View ->
    v.text.text = word
}