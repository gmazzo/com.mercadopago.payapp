package com.mercadopago.payapp

import android.app.Activity
import com.mercadopago.payapp.payments.models.Payment
import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.DispatchingAndroidInjector

@FlowScope
@Subcomponent(modules = [FlowModule::class])
interface FlowComponent {

    fun provideActivityInjector(): DispatchingAndroidInjector<Activity>

    @Subcomponent.Builder
    abstract class Builder {

        @BindsInstance
        abstract fun bindModel(model: Payment): Builder

        abstract fun build(): FlowComponent

        fun create(model: Payment = Payment()): FlowComponent {
            bindModel(model)
            return build()
        }

    }

}
