package com.mercadopago.payapp

import android.app.Activity
import com.mercadopago.payapp.data.DaggerDataComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import okhttp3.OkHttpClient

open class Application : DaggerApplication() {
    private val injector by lazy {
        DaggerApplicationInjector.builder()
                .dataComponent(DaggerDataComponent.builder()
                        .bind(this)
                        .bind(OkHttpClient.Builder())
                        .build())
                .create(this)!! as ApplicationInjector
    }

    var flowComponent: FlowComponent? = null
        get() {
            if (field == null) {
                field = injector.startNewFlow().create()
            }
            return field
        }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = injector

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return flowComponent!!.provideActivityInjector()
    }

}
