package com.mercadopago.test

import okhttp3.Interceptor
import okhttp3.Response

class DelayInterceptor(private val millis: Long) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Thread.sleep(millis)

        return chain.proceed(chain.request())
    }

}