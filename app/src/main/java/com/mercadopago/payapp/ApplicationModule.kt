package com.mercadopago.payapp

import android.content.Context
import dagger.Binds
import dagger.Module

@Module(subcomponents = [FlowComponent::class])
internal interface ApplicationModule {

    @Binds
    fun bindContext(impl: Application): Context

}
