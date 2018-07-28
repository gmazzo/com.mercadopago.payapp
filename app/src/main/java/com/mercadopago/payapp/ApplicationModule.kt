package com.mercadopago.payapp

import android.content.Context
import com.mercadopago.payapp.payments.PaymentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal interface ApplicationModule {

    @Binds
    fun bindContext(impl: Application): Context

    @ContributesAndroidInjector(modules = [PaymentModule::class])
    fun provideMainActivity(): MainActivity

}
