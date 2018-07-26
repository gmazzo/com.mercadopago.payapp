package com.mercadopago.payapp.payments.methods

import android.net.Uri
import com.mercadopago.payapp.BaseTest
import com.mercadopago.payapp.data.models.PaymentMethod
import com.mercadopago.payapp.data.payments.PaymentsRepository
import com.mercadopago.payapp.payments.models.Payment
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.net.SocketTimeoutException

internal class PaymentMethodsPresenterTest : BaseTest() {
    val TEST_URI = Uri.parse("http://test")
    val METHOD1 = PaymentMethod("1", "name1", TEST_URI, 10f, 40f, 0)
    val METHOD2 = PaymentMethod("2", "name2", TEST_URI, 0f, 100f, 2880)
    val METHOD3 = PaymentMethod("3", "name3", TEST_URI, 20f, 80f, 2880)

    lateinit var presenter: PaymentMethodPresenter

    @Mock
    lateinit var view: PaymentMethodContract.View

    @Mock
    lateinit var repository: PaymentsRepository

    @Test
    fun testOnStartLoading1() {
        testOnStartLoading(30f, METHOD1, METHOD2, METHOD3)
    }

    @Test
    fun testOnStartLoading2() {
        testOnStartLoading(4f, METHOD2)
    }

    @Test
    fun testOnStartLoading3() {
        testOnStartLoading(80f, METHOD2, METHOD3)
    }

    private fun testOnStartLoading(amount: Float, vararg expectedMethods: PaymentMethod) {
        `when`(repository.listMethods()).thenReturn(Single.just(listOf(METHOD1, METHOD2, METHOD3)))

        presenter = PaymentMethodPresenter(view, repository, Payment(amount = amount))
        presenter.onStartLoading()

        verify(repository).listMethods()
        verify(view).showMethods(expectedMethods.toList())
    }

    @Test
    fun testOnLoadMethods_Error() {
        val error = SocketTimeoutException()

        `when`(repository.listMethods()).thenReturn(Single.error(error))

        presenter = PaymentMethodPresenter(view, repository, Payment(amount = 100f))
        presenter.onStartLoading()

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
        presenter = PaymentMethodPresenter(view, repository, Payment(amount = amount))
        presenter.onMethodSelected(method)

        verify(view).showNextScreen(Payment(amount = amount, method = method))
    }

}
