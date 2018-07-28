package com.mercadopago.payapp

import com.mercadopago.test.DelayInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.mock.MockInterceptor
import java.util.concurrent.TimeUnit
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
                addInterceptor(DelayInterceptor(TimeUnit.SECONDS.toMillis(1)))
                addInterceptor(mockInterceptor)
            }

}
