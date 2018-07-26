package com.mercadopago.payapp.payments.banks

import android.support.v4.widget.CircularProgressDrawable
import android.view.ViewGroup
import com.mercadopago.payapp.R
import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.inflate
import com.mercadopago.utils.ItemsViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_payment_bank_item.view.*

internal class PaymentBankViewHolder(parent: ViewGroup) :
        ItemsViewHolder<PaymentBank>(parent.inflate(R.layout.fragment_payment_bank_item)) {

    override fun onItemBound(item: PaymentBank) {
        itemView.name.text = item.name
        Picasso.get()
                .load(item.image)
                .placeholder(CircularProgressDrawable(itemView.context))
                .error(R.drawable.ic_error)
                .into(itemView.logo)
    }

}
