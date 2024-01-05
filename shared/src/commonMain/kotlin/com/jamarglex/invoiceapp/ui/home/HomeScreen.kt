package com.jamarglex.invoiceapp.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jamarglex.invoiceapp.domain.Invoice
import com.jamarglex.invoiceapp.shared.Res
import com.jamarglex.invoiceapp.ui.details.DetailScreen
import com.jamarglex.invoiceapp.ui.login.LoginScreen

internal object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeViewModel = getScreenModel<HomeViewModel>()

        LaunchedEffect(Unit) {
            homeViewModel.navigationEvents.collect { destination ->
                when (destination) {
                    HomeViewModel.NavigationEvent.ToLogin -> navigator.push(LoginScreen)
                }
            }
        }

        Scaffold(
            topBar = { HomeTopBar(onMenuOverFlowClicked = {}) },
            content = { padding ->
                HomeScreenContent(
                    padding = padding,
                    uiState = homeViewModel.state,
                    onInvoiceClick = { navigator.push(DetailScreen(it)) }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navigator.push(DetailScreen(Invoice.NEW_INVOICE_ID)) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = Res.string.home_add_fab_content_description
                    )
                }
            }
        )
    }

}

@Composable
expect fun HomeScreenContent(
    padding: PaddingValues,
    uiState: HomeViewModel.UiState,
    onInvoiceClick: (Long) -> Unit
)
