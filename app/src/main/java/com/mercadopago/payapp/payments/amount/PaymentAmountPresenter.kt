package com.mercadopago.payapp.payments.amount

import com.mercadopago.payapp.payments.PaymentHeader
import com.mercadopago.payapp.payments.models.Payment
import javax.inject.Inject

internal class PaymentAmountPresenter @Inject constructor(
        private val view: PaymentAmountContract.View,
        private val header: PaymentHeader) : PaymentAmountContract.Presenter {

    override fun onStart() {
        header.updatePayment(null)
    }

    override fun onAmountEntered(amount: Float?) {
        if (amount?.takeIf { it > 0 } != null) {
            view.showNextScreen(Payment(amount = amount))

        } else {
            view.showAmountError()
        }
    }

}
