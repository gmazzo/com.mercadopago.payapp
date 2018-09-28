package com.mercadopago.payapp

import com.mercadopago.payapp.payments.PaymentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FlowModule {

    @ContributesAndroidInjector(modules = [PaymentModule::class])
    fun provideMainActivity(): MainActivity

}
