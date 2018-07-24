package com.mercadopago.apptest.data.payments

import com.mercadopago.apptest.data.models.PaymentMethod
import io.reactivex.Single

interface PaymentsRepository {

    fun listMethods(): Single<List<PaymentMethod>>

}
