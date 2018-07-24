package com.mercadopago.apptest.data.payments

import android.net.Uri
import com.mercadopago.apptest.data.models.PaymentMethod
import io.reactivex.Single
import javax.inject.Inject

internal class PaymentsDataSource @Inject constructor(private val api: PaymentsAPI) : PaymentsRepository {

    override fun listMethods(): Single<List<PaymentMethod>> =
            api.paymentMethods()
                    .map { it.filter { it.status.available }.map(this::convertMethod) }

    private fun convertMethod(method: PaymentsAPI.Method) = PaymentMethod(
            method.id,
            method.name,
            Uri.parse(method.thumbnail),
            method.minAmount,
            method.maxAmount)

}
