package com.jamarglex.invoiceapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jamarglex.invoiceapp.shared.Res

@Composable
actual fun BackButton(
    backButtonText: String,
    onBackClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(start = 32.dp, top = 16.dp)
            .clip(RoundedCornerShape(50))
            .clickable { onBackClicked() }
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(50)
            )
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = backButtonText.ifBlank { Res.string.back_button_content_description },
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(20.dp)
            )
            if (backButtonText.isNotBlank()) {
                Spacer(Modifier.padding(start = 8.dp))
                Text(
                    text = backButtonText,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
