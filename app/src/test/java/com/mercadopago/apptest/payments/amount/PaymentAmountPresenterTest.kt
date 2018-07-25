package com.mercadopago.apptest.payments.amount

import com.mercadopago.apptest.BaseTest
import com.mercadopago.apptest.payments.models.Payment
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
        testOnAmountEntered(100)
    }

    @Test
    fun testOnAmountEntered_200() {
        testOnAmountEntered(200)
    }

    private fun testOnAmountEntered(amount: Int) {
        presenter.onAmountEntered(amount)

        verify(view).showNextScreen(Payment(amount = amount))
    }

}
