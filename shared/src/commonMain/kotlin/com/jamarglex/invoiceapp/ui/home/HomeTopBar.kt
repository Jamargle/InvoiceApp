package com.jamarglex.invoiceapp.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.jamarglex.invoiceapp.shared.Res

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun HomeTopBar(onMenuOverFlowClicked: () -> Unit) {
    TopAppBar(
        title = { },
        actions = {
            IconButton(
                content = {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = Res.string.more_dots_menu_content_description
                    )
                },
                onClick = onMenuOverFlowClicked
            )
        }
    )
}
