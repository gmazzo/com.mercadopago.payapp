package com.mercadopago.payapp

import android.app.Application
import android.content.Context
import com.mercadopago.test.RxAwareAndroidJUnitRunner

class TestApplicationRunner : RxAwareAndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApplication::class.java.canonicalName, context)
    }

}