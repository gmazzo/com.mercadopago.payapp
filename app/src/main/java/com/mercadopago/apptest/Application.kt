package com.mercadopago.apptest

import dagger.android.support.DaggerApplication

class Application : DaggerApplication() {

    override fun applicationInjector() = DaggerApplicationInjector.builder().create(this)!!

}