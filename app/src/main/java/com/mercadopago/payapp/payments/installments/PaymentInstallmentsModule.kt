package com.mercadopago.payapp.payments.installments

import dagger.Binds
import dagger.Module

@Module
internal interface PaymentInstallmentsModule {

    @Binds
    fun bindPresenter(impl: PaymentInstallmentsPresenter): PaymentInstallmentsContract.Presenter

    @Binds
    fun bindView(impl: PaymentInstallmentsFragment): PaymentInstallmentsContract.View

}
