package com.jamarglex.invoiceapp

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", getPlatform().name.contains("Android"))
    }
}