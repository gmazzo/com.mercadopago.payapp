package com.mercadopago.payapp

import com.mercadopago.payapp.payments.PaymentFlowInstrumentedTest
import dagger.Component
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = [TestApplicationModule::class])
interface TestApplicationInjector {

    fun provideOkHttpClientBuilder(): OkHttpClient.Builder

    fun inject(impl: PaymentFlowInstrumentedTest)

}
