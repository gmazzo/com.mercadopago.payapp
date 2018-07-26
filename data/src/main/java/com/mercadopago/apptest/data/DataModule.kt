package com.mercadopago.apptest.data

import android.content.Context
import com.mercadopago.apptest.data.payments.PaymentsModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [PaymentsModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context) = OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            .addInterceptor(PublicKeyInterceptor(context.getString(R.string.meli_api_public_key)))
            .build()!!

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.ENDPOINT_URL)
            .build()!!

}
