package com.jamarglex.invoiceapp.ui

import androidx.compose.runtime.Composable

@Composable
expect fun BackButton(
    backButtonText: String,
    onBackClicked: () -> Unit
)
