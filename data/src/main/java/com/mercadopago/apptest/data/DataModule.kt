package com.mercadopago.apptest.data

import com.mercadopago.apptest.data.payments.PaymentsModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [PaymentsModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder) = builder
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.ENDPOINT_URL)
            .build()

}
