package com.mercadopago.test

import android.support.test.runner.AndroidJUnitRunner
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins

open class RxAwareAndroidJUnitRunner : AndroidJUnitRunner() {

    override fun onStart() {
        RxJavaPlugins.setInitComputationSchedulerHandler(Rx2Idler.create("rx-computation-idler"))
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("rx-io-idler"))
        RxJavaPlugins.setInitNewThreadSchedulerHandler(Rx2Idler.create("rx-new-thread-idler"))
        RxJavaPlugins.setInitSingleSchedulerHandler(Rx2Idler.create("rx-single-idler"))

        super.onStart()
    }

}
