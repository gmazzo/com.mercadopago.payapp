package com.mercadopago.payapp.data.source

import io.reactivex.Single
import retrofit2.http.GET

internal interface PaymentsAPI {

    @GET("payment_methods")
    fun paymentMethods(): Single<List<Method>>

}
