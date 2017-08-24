package com.rick.example.ui.binding

import android.view.View
import kotlinx.android.synthetic.main.item_string.view.*

/**
 * Created by rick on 18/8/2017.
 */

fun stringItemString(): (String, View) -> Unit = {
    s, v: View ->
    v.text.text = s
}


fun booleanItemString(): (Boolean, View) -> Unit = {
    b: Boolean, v: View ->
    v.text.text = b.toString()
}
