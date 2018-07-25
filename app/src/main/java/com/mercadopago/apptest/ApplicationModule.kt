package com.mercadopago.apptest

import android.content.Context
import com.mercadopago.apptest.data.DataModule
import com.mercadopago.apptest.payments.PaymentModule
import dagger.Binds
import dagger.Module

@Module(includes = [DataModule::class, PaymentModule::class])
internal interface ApplicationModule {

    @Binds
    fun bindContext(impl: Application): Context

}
