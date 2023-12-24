package com.jamarglex.invoiceapp.ui.details

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.core.parameter.parametersOf

data class DetailScreen(private val noteId: Long) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val detailsViewModel = getScreenModel<DetailsViewModel> { parametersOf(noteId) }
        Detail(
            viewModel = detailsViewModel,
            onClose = { navigator.pop() }
        )
    }

}

@Composable
expect fun Detail(viewModel: DetailsViewModel, onClose: () -> Unit)
