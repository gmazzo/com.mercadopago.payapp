package com.mercadopago.payapp.payments.methods

import android.support.v4.widget.CircularProgressDrawable
import android.view.ViewGroup
import com.mercadopago.payapp.R
import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.inflate
import com.mercadopago.payapp.toast
import com.mercadopago.utils.ItemsViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_payment_methods_item.view.*
import kotlin.math.ceil

internal class PaymentMethodViewHolder(parent: ViewGroup) :
        ItemsViewHolder<PaymentMethod>(parent.inflate(R.layout.fragment_payment_methods_item)) {

    override fun onItemBound(item: PaymentMethod) {
        itemView.contentDescription = item.name
        Picasso.get()
                .load(item.image)
                .placeholder(CircularProgressDrawable(itemView.context))
                .error(R.drawable.ic_error)
                .into(itemView.logo)

        with(itemView.context.resources) {
            val delay = ceil(item.accreditationMinutes / 60f).toInt()

            itemView.delay.text = when (delay) {
                0 -> getText(R.string.payment_method_delay_instant)
                else -> getQuantityString(R.plurals.payment_method_delay, delay, delay)
            }
        }

        itemView.setOnLongClickListener {
            item.name.toast(itemView.context)
            return@setOnLongClickListener true
        }
    }

}
