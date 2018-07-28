package com.mercadopago.payapp

import com.mercadopago.payapp.data.DaggerDataComponent
import dagger.android.support.DaggerApplication
import okhttp3.OkHttpClient

open class Application : DaggerApplication() {

    override fun applicationInjector() = DaggerApplicationInjector.builder()
            .dataComponent(DaggerDataComponent.builder()
                    .bind(this)
                    .bind(OkHttpClient.Builder())
                    .build())
            .create(this)!!

}
