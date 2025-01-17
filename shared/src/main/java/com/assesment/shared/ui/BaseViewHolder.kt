package com.assesment.shared.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T, VB : ViewBinding> constructor(vb: VB) :
    RecyclerView.ViewHolder(vb.root) {
    private var item: T? = null

    abstract fun bind()

    fun bindItems(item: T) = apply {
        this.item = item
    }

    fun getRowItem(): T? = item
}