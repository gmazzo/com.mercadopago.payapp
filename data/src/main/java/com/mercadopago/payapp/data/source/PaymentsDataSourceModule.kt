package com.mercadopago.payapp.data.source

import com.mercadopago.payapp.data.PaymentsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class PaymentsDataSourceModule {

    @Binds
    internal abstract fun bindPaymentsRepository(impl: PaymentsDataSource): PaymentsRepository

    @Module
    companion object {

        @JvmStatic
        @Provides
        internal fun providePaymentsAPI(retrofit: Retrofit) = retrofit.create(PaymentsAPI::class.java)

    }

}
