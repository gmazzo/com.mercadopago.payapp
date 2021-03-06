package com.mercadopago.payapp.data.source

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PaymentsAPI {

    @GET("payment_methods")
    fun paymentMethods(): Single<List<Method>>

    @GET("payment_methods/card_issuers")
    fun cardIssuers(@Query("payment_method_id") paymentMethodId: String): Single<List<Bank>>

    @GET("payment_methods/installments")
    fun installments(@Query("amount") amount: Float,
                     @Query("payment_method_id") paymentMethodId: String,
                     @Query("issuer.id") issuerId: Int): Single<List<Installments>>

}
