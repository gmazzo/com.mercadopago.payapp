package com.mercadopago.apptest.data

import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [DataModule::class])
interface DataComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindRetrofit(builder: Retrofit.Builder)

        fun build(): DataComponent

    }

}
