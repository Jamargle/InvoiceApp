package com.jamarglex.invoiceapp.ui.details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.jamarglex.invoiceapp.shared.Res

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailScreenTopBar(
    toolbarText: String,
    onNavigationIconClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(toolbarText) },
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClicked
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = Res.string.back_button_content_description
                )
            }
        }
    )
}
