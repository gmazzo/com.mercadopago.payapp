package com.mercadopago.payapp.payments

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.mercadopago.payapp.MainActivity
import com.mercadopago.payapp.R
import com.mercadopago.test.textInputLayoutError
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PaymentFlowInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testFullFlow() {
        checkHeader()
        setAmount(123.45f) // sets amount

        checkHeader("$ 123.45")
        selectRecyclerItem(0) // selects Visa

        checkHeader("$ 123.45", "Visa")
        selectRecyclerItem(2) // selects Galicia

        checkHeader("$ 123.45", "Visa", "Banco Galicia")
        selectRecyclerItem(4) // selects 12 cuotas

        checkHeader()
        checkHeader("$ 123.45", "Visa", "Banco Galicia", "12 cuotas", R.id.fragmentContainer)
        onView(withId(R.id.pay))
                .check(matches(isDisplayed()))
                .perform(click()) // click on "Pay" button
                .check(matches(not(isDisplayed())))

        onView(withId(R.id.paid))
                .check(matches(isDisplayed()))
    }

    @Test
    fun testNoAmount() {
        testInvalidAmount(null)
    }

    @Test
    fun testZeroAmount() {
        testInvalidAmount(0f)
    }

    private fun testInvalidAmount(amount: Float?) {
        checkHeader()
        setAmount(amount) // sets amount

        onView(textInputLayoutError(withId(R.id.amount)))
                .check(matches(withText("Ingrese un monto válido")))
    }

    private fun checkHeader(amount: String? = "", method: String = "", bank: String = "", installments: String = "",
                            @IdRes fragmentId: Int = R.id.fragmentHeaderContainer) {
        val fragment = isDescendantOfA(withId(fragmentId))

        onView(allOf(withId(R.id.amount), fragment))
                .check(matches(withText(amount)))
        onView(allOf(withId(R.id.method), fragment))
                .check(matches(withText(method)))
        onView(allOf(withId(R.id.bank), fragment))
                .check(matches(withText(bank)))
        onView(allOf(withId(R.id.installments), fragment))
                .check(matches(withText(installments)))
    }

    private fun setAmount(amount: Float?) {
        onView(withId(R.id.amountValue))
                .perform(typeText(amount?.toString() ?: ""))

        onView(withId(R.id.next)).perform(click()) // click on "Continue" button
    }

    private fun selectRecyclerItem(position: Int) {
        onView(withId(R.id.recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
    }

}
