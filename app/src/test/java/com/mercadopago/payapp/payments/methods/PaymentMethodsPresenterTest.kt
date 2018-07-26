package com.mercadopago.payapp.payments.methods

import android.net.Uri
import com.mercadopago.payapp.BaseTest
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

internal class PaymentMethodsPresenterTest : BaseTest() {

    private lateinit var presenter: PaymentMethodPresenter

    @Mock
    private lateinit var view: PaymentMethodContract.View

    @Mock
    private lateinit var header: PaymentHeader

    @Mock
    private lateinit var repository: PaymentsRepository

    @Test
    fun testOnStart1() {
        testOnStart(30f, METHOD1, METHOD2, METHOD3)
    }

    @Test
    fun testOnStart2() {
        testOnStart(4f, METHOD2)
    }

    @Test
    fun testOnStart3() {
        testOnStart(80f, METHOD2, METHOD3)
    }

    private fun testOnStart(amount: Float, vararg expectedMethods: PaymentMethod) {
        val payment = Payment(amount = amount)

        `when`(repository.listMethods()).thenReturn(Single.just(listOf(METHOD1, METHOD2, METHOD3)))

        presenter = PaymentMethodPresenter(view, header, repository, payment)
        presenter.onStart()

        verify(repository).listMethods()
        verify(header).updatePayment(payment)
        verify(view).showMethods(expectedMethods.toList())
    }

    @Test
    fun testOnLoadMethods_Error() {
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
        testOnMethodSelected(100f, METHOD1)
    }

    @Test
    fun testOnMethodSelected2() {
        testOnMethodSelected(200f, METHOD3)
    }

    private fun testOnMethodSelected(amount: Float, method: PaymentMethod) {
        presenter = PaymentMethodPresenter(view, header, repository, Payment(amount = amount))
        presenter.onMethodSelected(method)

        verify(view).showNextScreen(Payment(amount = amount, method = method))
    }

    companion object {

        private val TEST_URI = Uri.parse("http://test")
        private val METHOD1 = PaymentMethod("1", "name1", TEST_URI, 10f, 40f, 0)
        private val METHOD2 = PaymentMethod("2", "name2", TEST_URI, 0f, 100f, 2880)
        private val METHOD3 = PaymentMethod("3", "name3", TEST_URI, 20f, 80f, 2880)

    }

}
