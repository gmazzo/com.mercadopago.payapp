package com.mercadopago.payapp.payments.amount

import com.mercadopago.payapp.BaseTest
import com.mercadopago.payapp.payments.models.Payment
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify

internal class PaymentAmountPresenterTest : BaseTest() {

    @InjectMocks
    lateinit var presenter: PaymentAmountPresenter

    @Mock
    lateinit var view: PaymentAmountContract.View

    @Test
    fun testOnAmountEntered_100() {
        testOnAmountEntered(100f, false)
    }

    @Test
    fun testOnAmountEntered_200() {
        testOnAmountEntered(200f, false)
    }

    @Test
    fun testOnAmountEntered_Null() {
        testOnAmountEntered(null, true)
    }

    @Test
    fun testOnAmountEntered_0() {
        testOnAmountEntered(0f, true)
    }

    @Test
    fun testOnAmountEntered_Negative() {
        testOnAmountEntered(-1f, true)
    }

    private fun testOnAmountEntered(amount: Float?, shouldFail: Boolean) {
        presenter.onAmountEntered(amount)

        if (shouldFail) {
            verify(view).showAmountError()

        } else {
            verify(view).showNextScreen(Payment(amount = amount))
        }
    }

}
