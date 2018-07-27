package com.mercadopago.payapp.payments.methods

import com.mercadopago.payapp.BaseTest
import com.mercadopago.payapp.TEST_METHOD1
import com.mercadopago.payapp.TEST_METHOD2
import com.mercadopago.payapp.TEST_METHOD3
import com.mercadopago.payapp.data.PaymentsRepository
import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.payments.PaymentHeader
import com.mercadopago.payapp.payments.models.Payment
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.net.SocketTimeoutException

internal class PaymentMethodPresenterTest : BaseTest() {
    private lateinit var presenter: PaymentMethodPresenter

    @Mock
    private lateinit var view: PaymentMethodContract.View

    @Mock
    private lateinit var header: PaymentHeader

    @Mock
    private lateinit var repository: PaymentsRepository

    @Test
    fun testOnStart1() {
        testOnStart(30f, TEST_METHOD1, TEST_METHOD2, TEST_METHOD3)
    }

    @Test
    fun testOnStart2() {
        testOnStart(4f, TEST_METHOD2)
    }

    @Test
    fun testOnStart3() {
        testOnStart(80f, TEST_METHOD2, TEST_METHOD3)
    }

    private fun testOnStart(amount: Float, vararg expectedMethods: PaymentMethod) {
        val payment = Payment(amount = amount)

        `when`(repository.listMethods()).thenReturn(Single.just(listOf(TEST_METHOD1, TEST_METHOD2, TEST_METHOD3)))

        presenter = PaymentMethodPresenter(view, header, repository, payment)
        presenter.onStart()

        verify(repository).listMethods()
        verify(header).updatePayment(payment)
        verify(view).showMethods(expectedMethods.toList())
    }

    @Test
    fun testOnStart_Error() {
        val payment = Payment(amount = 100f)
        val error = SocketTimeoutException()

        `when`(repository.listMethods()).thenReturn(Single.error(error))

        presenter = PaymentMethodPresenter(view, header, repository, payment)
        presenter.onStart()

        verify(header).updatePayment(payment)
        verify(view).showError(error)
    }

    @Test
    fun testOnMethodSelected1() {
        testOnMethodSelected(100f, TEST_METHOD1)
    }

    @Test
    fun testOnMethodSelected2() {
        testOnMethodSelected(200f, TEST_METHOD3)
    }

    private fun testOnMethodSelected(amount: Float, method: PaymentMethod) {
        presenter = PaymentMethodPresenter(view, header, repository, Payment(amount = amount))
        presenter.onMethodSelected(method)

        verify(view).showNextScreen(Payment(amount = amount, method = method))
    }

}
