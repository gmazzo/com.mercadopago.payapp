package com.mercadopago.apptest

import android.net.Uri
import okhttp3.OkHttpClient
import okhttp3.mock.MockInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun mockRetrofit(interceptor: MockInterceptor) = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build())
        .baseUrl("http://dummy")
        .build()!!

fun String.toUri() = Uri.parse(this)!!
