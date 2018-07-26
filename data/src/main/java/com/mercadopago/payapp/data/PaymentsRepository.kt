package com.mercadopago.payapp.data

import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.data.models.PaymentMethod
import io.reactivex.Single

interface PaymentsRepository {

    fun listMethods(): Single<List<PaymentMethod>>

    fun cardIssuers(method: PaymentMethod): Single<List<PaymentBank>>

}
