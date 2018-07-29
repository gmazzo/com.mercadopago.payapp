package com.mercadopago.payapp

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.mock.MockInterceptor
import javax.inject.Singleton

@Module
class TestApplicationModule {

    @Provides
    @Singleton
    fun provideMockInterceptor() = MockInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(mockInterceptor: MockInterceptor) =
            OkHttpClient.Builder().apply {
                addInterceptor(mockInterceptor)
            }

}
