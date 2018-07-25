package com.mercadopago.apptest.payments.methods

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.mercadopago.apptest.ARG_PAYMENT
import com.mercadopago.apptest.R
import com.mercadopago.apptest.payments.models.Payment

class PaymentMethodsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.fragment_payment_methods, container, false)!!

    companion object {

        fun create(payment: Payment) = PaymentMethodsFragment().apply {
            arguments = bundleOf(ARG_PAYMENT to payment)
        }

    }

}
