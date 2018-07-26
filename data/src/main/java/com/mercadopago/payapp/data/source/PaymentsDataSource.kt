package com.mercadopago.payapp.data.source

import android.net.Uri
import com.mercadopago.payapp.data.PaymentsRepository
import com.mercadopago.payapp.data.models.PaymentBank
import com.mercadopago.payapp.data.models.PaymentMethod
import javax.inject.Inject

internal class PaymentsDataSource @Inject constructor(
        private val api: PaymentsAPI) : PaymentsRepository {

    override fun listMethods() =
            api.paymentMethods().map {
                it.filter { it.status.available }.map {
                    PaymentMethod(it.id, it.name, Uri.parse(it.thumbnail),
                            it.minAmount, it.maxAmount, it.accreditationTime)
                }
            }!!

    override fun cardIssuers(method: PaymentMethod) =
            api.cardIssuers(method.id).map {
                it.map { PaymentBank(it.id, it.name, Uri.parse(it.thumbnail)) }
            }!!

}
