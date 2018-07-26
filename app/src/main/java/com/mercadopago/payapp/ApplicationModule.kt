package com.mercadopago.payapp

import android.content.Context
import com.mercadopago.payapp.data.DataModule
import com.mercadopago.payapp.payments.PaymentModule
import dagger.Binds
import dagger.Module

@Module(includes = [DataModule::class, PaymentModule::class])
internal interface ApplicationModule {

    @Binds
    fun bindContext(impl: Application): Context

}
