package com.jamarglex.invoiceapp.shared

import com.jamarglex.invoiceapp.Platform
import com.jamarglex.invoiceapp.getPlatform

internal class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
