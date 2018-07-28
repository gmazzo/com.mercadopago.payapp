package com.mercadopago.payapp.data

import android.content.Context
import com.mercadopago.payapp.data.source.PaymentsDataSourceModule
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [PaymentsDataSourceModule::class])
internal class DataModule {

    @Provides
    fun provideOkHttpClient(context: Context, clientBuilder: OkHttpClient.Builder) = clientBuilder
            .addInterceptor(PublicKeyInterceptor(context.getString(R.string.meli_api_public_key)))
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
            .build()!!

    @Provides
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.ENDPOINT_URL)
            .build()!!

}
