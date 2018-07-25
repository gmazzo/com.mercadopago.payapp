package com.mercadopago.apptest

import com.mercadopago.apptest.data.DataModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [DataModule::class])
internal interface ApplicationModule {

    @ContributesAndroidInjector
    fun provideMainActivity(): MainActivity

}
