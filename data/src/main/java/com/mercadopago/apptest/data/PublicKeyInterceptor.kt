package com.mercadopago.apptest.data

import okhttp3.Interceptor

class PublicKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) =
            chain.proceed(chain.request().run {
                newBuilder()
                        .url(url().newBuilder()
                                .addQueryParameter("public_key", BuildConfig.PUBLIC_KEY)
                                .build())
                        .build()
            })!!

}
