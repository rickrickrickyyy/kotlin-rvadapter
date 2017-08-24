package com.rick.example.ui.binding

import android.content.Context
import android.view.View
import com.rick.example.R
import com.rick.example.ui.dataclass.Word
import com.rick.onetrueadapter.LayoutsAdapter

/**
 * Created by rick on 24/8/2017.
 */
inline fun <reified DATA : Any> toAnyBindFun(crossinline f: (DATA, View) -> Unit): (Any, View) -> Unit = {
    i: Any, v: View ->
    when (i) {
        is DATA -> f(i, v)
        else -> {
        }
    }
}

fun MainActivityViewType() = {
    i: Any ->
    when (i) {
        is Boolean -> R.layout.item_string
        is String -> R.layout.item_string
        is Word -> R.layout.item_word
        is Int -> R.layout.item_word
        else -> {
            R.layout.item_word
        }
    }
}

fun MainActivityBindFun(): (Any, View) -> Unit = {
    i: Any, v: View ->
    when (i) {
        is Boolean -> booleanItemString()(i, v)
        is String -> stringItemString()(i, v)
        is Int -> intItemWord()(i, v)
        is Word -> wordItemWord()(i, v)
        else -> {
        }
    }
}

fun MainActivityAdapter(context: Context, d: MutableList<Any>): LayoutsAdapter<Any> {
    return LayoutsAdapter(context, d, MainActivityViewType(), MainActivityBindFun())
}