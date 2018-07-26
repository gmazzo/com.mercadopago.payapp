package com.mercadopago.payapp.data.source

import android.net.Uri
import com.mercadopago.payapp.data.PaymentsRepository
import com.mercadopago.payapp.data.models.PaymentMethod
import io.reactivex.Single
import javax.inject.Inject

internal class PaymentsDataSource @Inject constructor(
        private val api: PaymentsAPI) : PaymentsRepository {

    override fun listMethods(): Single<List<PaymentMethod>> =
            api.paymentMethods().map {
                it.filter { it.status.available }.map { it.toMethod() }
            }

    private fun Method.toMethod() =
            PaymentMethod(id, name, Uri.parse(thumbnail), minAmount, maxAmount, accreditationTime)

}
