package com.jamarglex.invoiceapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
