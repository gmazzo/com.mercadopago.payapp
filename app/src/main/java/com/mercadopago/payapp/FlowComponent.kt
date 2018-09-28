package com.mercadopago.payapp

import android.app.Activity
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
        abstract fun bindStorage(storage: FlowStorage): Builder

        abstract fun build(): FlowComponent

        fun create(storage: FlowStorage = FlowStorage()): FlowComponent {
            bindStorage(storage)
            return build()
        }

    }

}
