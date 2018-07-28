package com.mercadopago.payapp.data

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient

@Component(modules = [DataModule::class])
interface DataComponent {

    fun providePaymentsRepository(): PaymentsRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bind(context: Context): Builder

        @BindsInstance
        fun bind(clientBuilder: OkHttpClient.Builder): Builder

        fun build(): DataComponent

    }

}
