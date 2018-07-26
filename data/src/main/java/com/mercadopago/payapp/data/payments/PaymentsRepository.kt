package com.mercadopago.payapp.data.payments

import com.mercadopago.payapp.data.models.PaymentMethod
import io.reactivex.Single

interface PaymentsRepository {

    fun listMethods(): Single<List<PaymentMethod>>

}
