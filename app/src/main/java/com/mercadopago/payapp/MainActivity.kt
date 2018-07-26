package com.mercadopago.payapp

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.util.AttributeSet
import com.mercadopago.payapp.payments.PaymentHeaderFragment
import com.mercadopago.payapp.payments.amount.PaymentAmountFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentHeaderContainer, PaymentHeaderFragment())
                    .add(R.id.fragmentContainer, PaymentAmountFragment())
                    .commitNow()
        }
    }

    class OverlayScrollingViewBehavior(context: Context, attrs: AttributeSet) : AppBarLayout.ScrollingViewBehavior(context, attrs) {

    }

}
