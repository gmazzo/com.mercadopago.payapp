package com.mercadopago.payapp.payments.installments

import android.view.ViewGroup
import com.mercadopago.payapp.R
import com.mercadopago.payapp.data.models.PaymentInstallments
import com.mercadopago.payapp.inflate
import com.mercadopago.utils.ItemsViewHolder
import kotlinx.android.synthetic.main.fragment_payment_installments_item.view.*

internal class PaymentInstallmentsViewHolder(parent: ViewGroup) :
        ItemsViewHolder<PaymentInstallments>(parent.inflate(R.layout.fragment_payment_installments_item)) {

    override fun onItemBound(item: PaymentInstallments) {
        itemView.message.text = item.message
    }

}
