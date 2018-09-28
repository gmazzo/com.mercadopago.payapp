package com.mercadopago.payapp

import com.mercadopago.payapp.data.DataComponent
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        dependencies = [DataComponent::class],
        modules = [AndroidSupportInjectionModule::class, ApplicationModule::class])
internal interface ApplicationInjector : AndroidInjector<Application> {

    fun startNewFlow(): FlowComponent.Builder

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Application>() {

        abstract fun dataComponent(dataComponent: DataComponent): Builder

        abstract override fun build(): ApplicationInjector

    }

}
