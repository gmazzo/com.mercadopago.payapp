package com.mercadopago.payapp.payments.resume

import com.mercadopago.payapp.payments.PaymentHeader
import com.mercadopago.payapp.payments.models.Payment
import javax.inject.Inject

internal class PaymentResumePresenter @Inject constructor(
        private val view: PaymentResumeContract.View,
        private val header: PaymentHeader,
        private val payment: Payment) : PaymentResumeContract.Presenter {

    override fun onStart() {
        header.updatePayment(null)
        view.showPayment(payment)
    }

}
