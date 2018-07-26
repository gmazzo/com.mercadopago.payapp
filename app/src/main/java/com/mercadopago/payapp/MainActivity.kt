package com.mercadopago.payapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mercadopago.payapp.payments.amount.PaymentAmountFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, PaymentAmountFragment.create())
                    .commitNow()
        }
    }

}
