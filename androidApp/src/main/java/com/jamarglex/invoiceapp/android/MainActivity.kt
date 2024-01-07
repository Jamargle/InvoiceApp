package com.jamarglex.invoiceapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.Firebase
import com.google.firebase.initialize
import com.jamarglex.invoiceapp.App
import com.jamarglex.invoiceapp.ui.theme.InvoiceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.initialize(this)
        setContent {
            InvoiceAppTheme {
                App()
            }
        }
    }
}
