package com.mercadopago.utils

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class ItemsViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

    var item: T? = null
        set(value) {
            field = value!!

            onItemBound(value)
        }

    protected abstract fun onItemBound(item: T)

}
