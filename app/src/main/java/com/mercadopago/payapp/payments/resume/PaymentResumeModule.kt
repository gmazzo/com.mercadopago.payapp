package com.mercadopago.payapp.payments.resume

import dagger.Binds
import dagger.Module

@Module
internal interface PaymentResumeModule {

    @Binds
    fun bindPresenter(impl: PaymentResumePresenter): PaymentResumeContract.Presenter

    @Binds
    fun bindView(impl: PaymentResumeFragment): PaymentResumeContract.View

}
