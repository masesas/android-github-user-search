package com.assesment.shared.ui

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<T, VB : ViewBinding, VH : BaseViewHolder<T, VB>>(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindItems(getItem(position))
        holder.bind()
    }

    override fun submitList(list: MutableList<T>?) {
        super.submitList(list ?: emptyList())
    }
}