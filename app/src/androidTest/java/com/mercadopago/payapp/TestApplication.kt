package com.mercadopago.payapp

import android.support.test.InstrumentationRegistry
import com.mercadopago.payapp.data.DaggerDataComponent

class TestApplication : Application() {
    private val injector by lazy { DaggerTestApplicationInjector.create() }

    override fun applicationInjector() = DaggerApplicationInjector.builder()
            .dataComponent(DaggerDataComponent.builder()
                    .bind(this)
                    .bind(injector.provideOkHttpClientBuilder())
                    .build())
            .create(this)!!

    companion object {

        fun injector() =
                (InstrumentationRegistry.getTargetContext().applicationContext as TestApplication).injector!!

    }

}
