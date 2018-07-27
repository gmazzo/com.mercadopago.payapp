package com.mercadopago.test

import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA
import android.view.View
import android.widget.EditText
import android.widget.TextView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

fun textInputLayoutError(view: Matcher<View>) =
        allOf(isDescendantOfA(view), not(isAssignableFrom(EditText::class.java)), isAssignableFrom(TextView::class.java))!!
