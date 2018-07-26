package com.mercadopago.payapp.payments

import com.mercadopago.payapp.payments.models.Payment

internal interface PaymentHeader {

    fun updatePayment(payment: Payment?)

}
