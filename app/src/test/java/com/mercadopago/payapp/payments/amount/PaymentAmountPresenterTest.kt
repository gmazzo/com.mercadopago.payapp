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
    fun testOnAmountEntered_Null() {
        presenter.onAmountEntered(null)

        verify(view).showAmountError()
    }

    @Test
    fun testOnAmountEntered_100() {
        testOnAmountEntered(100f)
    }

    @Test
    fun testOnAmountEntered_200() {
        testOnAmountEntered(200f)
    }

    private fun testOnAmountEntered(amount: Float) {
        presenter.onAmountEntered(amount)

        verify(view).showNextScreen(Payment(amount = amount))
    }

}
