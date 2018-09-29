package com.mercadopago.payapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.resetFlow -> resetFlow()
        else -> super.onOptionsItemSelected(item)
    }

    private fun resetFlow(): Boolean {
        (application as Application).flowComponent = null
        startActivity(Intent(this, javaClass))
        return true
    }

}
