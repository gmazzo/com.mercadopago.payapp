package com.mercadopago.payapp.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.mercadopago.payapp.R
import com.mercadopago.payapp.payments.models.Payment
import com.mercadopago.payapp.textOrHide
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_payment_header.*

class PaymentHeaderFragment : DaggerFragment(), PaymentHeader {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_payment_header, container, false)!!

    override fun updatePayment(payment: Payment?) {
        bindText(amount, amountLabel, payment?.amount?.let { getString(R.string.payment_amount_with_currency, it) })
        bindText(method, methodLabel, payment?.method?.name)
        bindText(bank, bankLabel, payment?.bank?.name)
        bindText(installments, installmentsLabel, payment?.installments?.let { resources.getQuantityString(R.plurals.payment_installments, it, it) })
        view!!.isVisible = amount.isVisible
    }

    private fun bindText(view: TextView, label: View, text: CharSequence?) {
        view.textOrHide = text
        label.isVisible = view.isVisible
    }

}
