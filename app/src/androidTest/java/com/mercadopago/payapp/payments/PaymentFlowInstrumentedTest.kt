package com.mercadopago.payapp.payments

import android.support.annotation.IdRes
import android.support.annotation.RawRes
import android.support.test.InstrumentationRegistry
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
import com.mercadopago.payapp.TestApplication.Companion.injector
import com.mercadopago.test.textInputLayoutError
import okhttp3.mock.AndroidResources.rawRes
import okhttp3.mock.MockInterceptor
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import com.mercadopago.payapp.test.R as testR

@RunWith(AndroidJUnit4::class)
class PaymentFlowInstrumentedTest {

    @Inject
    lateinit var mockInterceptor: MockInterceptor

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        injector().inject(this)
    }

    @Test
    fun testFullFlow() {
        checkHeader()
        mockRequest("https://api.mercadopago.com/v1/payment_methods", testR.raw.payment_methods)
        setAmount(123.45f) // sets amount

        checkHeader("$ 123.45")
        mockRequest("https://api.mercadopago.com/v1/payment_methods/card_issuers?payment_method_id=visa", testR.raw.card_issuers)
        selectRecyclerItem(0) // selects Visa

        checkHeader("$ 123.45", "Visa")
        mockRequest("https://api.mercadopago.com/v1/payment_methods/installments?amount=123.45&payment_method_id=visa&issuer.id=279", testR.raw.installments)
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

        onView(withId(R.id.next))
                .perform(click()) // click on "Continue" button
    }

    private fun selectRecyclerItem(position: Int) {
        onView(withId(R.id.recycler))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
    }

    private fun mockRequest(url: String, @RawRes responseId: Int) {
        mockInterceptor.addRule()
                .get(url)
                .delay(TimeUnit.SECONDS.toMillis(1))
                .respond(rawRes(InstrumentationRegistry.getContext(), responseId))
    }

}
