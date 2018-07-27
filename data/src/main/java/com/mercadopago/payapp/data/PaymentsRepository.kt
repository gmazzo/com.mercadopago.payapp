package com.mercadopago.payapp.data

import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.data.models.PaymentInstallments
import com.mercadopago.payapp.data.models.PaymentMethod
import io.reactivex.Single

interface PaymentsRepository {

    fun listMethods(): Single<List<PaymentMethod>>

    fun cardIssuers(method: PaymentMethod): Single<List<PaymentBank>>

    fun installments(amount: Float, method: PaymentMethod, bank: PaymentBank): Single<List<PaymentInstallments>>

}
