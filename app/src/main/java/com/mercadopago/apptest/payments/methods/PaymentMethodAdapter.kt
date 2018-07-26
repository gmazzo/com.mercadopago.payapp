package com.mercadopago.apptest.payments.methods

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.mercadopago.apptest.R
import com.mercadopago.apptest.data.models.PaymentMethod
import com.mercadopago.apptest.inflate

internal class PaymentMethodAdapter(
        private val items: List<PaymentMethod>,
        private val selectionListener: (PaymentMethod) -> Unit) : RecyclerView.Adapter<PaymentMethodViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PaymentMethodViewHolder(parent.inflate(R.layout.fragment_payment_methods_item)).apply {
                itemView.setOnClickListener {
                    selectionListener.invoke(item!!)
                }
            }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        holder.item = items[position]
    }

}
