package com.mercadopago.apptest.payments

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.mercadopago.apptest.R

class PaymentPagerAdapter(context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val titles = context.resources.getTextArray(R.array.payments_steps)

    override fun getCount() = titles.size

    override fun getPageTitle(position: Int) = titles[position]!!

    override fun getItem(position: Int) = when (position) {
        0 -> PaymentAmountFragment()
        1 -> PaymentMethodsFragment()

        else -> throw NotImplementedError("Unknown fragment at position $position")
    }

}
