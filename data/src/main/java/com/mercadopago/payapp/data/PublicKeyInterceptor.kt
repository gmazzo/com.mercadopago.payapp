package com.mercadopago.payapp.data

import okhttp3.Interceptor

internal class PublicKeyInterceptor(private val publicKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) =
            chain.proceed(chain.request().run {
                newBuilder()
                        .url(url().newBuilder()
                                .addQueryParameter("public_key", publicKey)
                                .build())
                        .build()
            })!!

}
