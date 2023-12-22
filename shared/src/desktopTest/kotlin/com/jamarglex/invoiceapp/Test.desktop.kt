package com.jamarglex.invoiceapp

import com.jamarglex.invoiceapp.shared.Greeting
import kotlin.test.Test
import kotlin.test.assertTrue

class DesktopGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greet().contains("Hello"), "Check 'Hello' is mentioned")
    }
}