package com.mercadopago.apptest.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mercadopago.apptest.R
import com.mercadopago.apptest.payments.models.Payment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_payment_amount.*
import javax.inject.Inject

internal class PaymentAmountFragment : DaggerFragment() {

    @Inject
    lateinit var payment: Payment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_payment_amount, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        amount.editText!!.setOnEditorActionListener { _, _, _ -> next.performClick() }

        next.setOnClickListener {
            payment.amount = amount.editText!!.text.toString().toInt()
        }
    }

}
