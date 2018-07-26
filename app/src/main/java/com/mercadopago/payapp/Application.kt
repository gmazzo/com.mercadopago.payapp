package com.mercadopago.payapp

import dagger.android.support.DaggerApplication

class Application : DaggerApplication() {

    override fun applicationInjector() = DaggerApplicationInjector.builder().create(this)!!

}