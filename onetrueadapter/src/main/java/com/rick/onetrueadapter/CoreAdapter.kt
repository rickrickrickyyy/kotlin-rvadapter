package com.rick.onetrueadapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by rick on 11/8/2017.
 *
 *
 */
open class CoreAdapter<DATA, BRIDGE, VH : BaseViewHolder<DATA, BRIDGE>>(val data: MutableList<DATA>,
                                                                        val viewHolders: (Int) -> VH,
                                                                        val viewTypes: ((DATA) -> Int)?) : Adapter<VH>() {
    override fun getItemViewType(position: Int): Int {
        return viewTypes?.invoke(data[position]) ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH {
        return viewHolders(viewType)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VH?, position: Int) {
        holder?.bindFun?.invoke(data[position], holder.binding)
    }
}

open class LayoutsAdapter<DATA>(context: Context, data: MutableList<DATA>,
                                viewTypes: ((DATA) -> Int)?, bindFun: BindFun<DATA, View>) :
        CoreAdapter<DATA, View, LayoutViewHolder<DATA>>(data, { i: Int -> LayoutViewHolder(context, i, bindFun) }, viewTypes)

abstract class BaseViewHolder<in DATA, BRIDGE>(rootView: (BRIDGE) -> View, val binding: BRIDGE, val bindFun: BindFun<DATA, BRIDGE>)
    : RecyclerView.ViewHolder(rootView(binding))

open class LayoutViewHolder<in DATA>(context: Context, layout: Int, bindFun: BindFun<DATA, View>) :
        BaseViewHolder<DATA, View>({ v: View -> v }, LayoutInflater.from(context).inflate(layout, null, false), bindFun)

typealias BindFun<DATA, BRIDGE> = (DATA, BRIDGE) -> Unit