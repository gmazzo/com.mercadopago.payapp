package com.mercadopago.payapp.payments.methods

import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mercadopago.payapp.R
import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_payment_methods_item.view.*
import kotlin.math.ceil

internal class PaymentMethodViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var item: PaymentMethod? = null
        set(value) {
            field = value!!

            itemView.contentDescription = value.name
            Picasso.get()
                    .load(value.image)
                    .placeholder(CircularProgressDrawable(itemView.context))
                    .error(R.drawable.ic_error)
                    .into(itemView.logo)

            with(itemView.context.resources) {
                val delay = ceil(value.accreditationMinutes / 60f).toInt()

                itemView.delay.text = when (delay) {
                    0 -> getText(R.string.payment_method_delay_instant)
                    else -> getQuantityString(R.plurals.payment_method_delay, delay, delay)
                }
            }

            itemView.setOnLongClickListener {
                value.name.toast(itemView.context)
                return@setOnLongClickListener true
            }
        }

}
