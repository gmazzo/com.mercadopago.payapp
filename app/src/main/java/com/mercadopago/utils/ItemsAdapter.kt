package com.mercadopago.utils

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

open class ItemsAdapter<T, VH : ItemsViewHolder<T>>(
        private val items: List<T>,
        private val selectionListener: (T) -> Unit,
        private val holderProvider: (ViewGroup) -> VH) : RecyclerView.Adapter<VH>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            holderProvider.invoke(parent).apply {
                itemView.setOnClickListener {
                    selectionListener.invoke(item!!)
                }
            }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.item = items[position]
    }

}
