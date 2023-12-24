package com.jamarglex.invoiceapp.ui.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jamarglex.invoiceapp.ui.details.DetailScreen

object HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeViewModel = getScreenModel<HomeViewModel>()
        Home(
            viewModel = homeViewModel,
            onInvoiceClick = { navigator.push(DetailScreen(it)) }
        )
    }

}

@Composable
expect fun Home(viewModel: HomeViewModel, onInvoiceClick: (Long) -> Unit)
