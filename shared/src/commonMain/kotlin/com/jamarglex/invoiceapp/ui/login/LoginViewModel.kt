package com.jamarglex.invoiceapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jamarglex.invoiceapp.domain.SessionRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val sessionRepository: SessionRepository
) : ScreenModel {

    private val navigationChannel = Channel<NavigationEvent>(Channel.BUFFERED)
    val navigationEvents: Flow<NavigationEvent> = navigationChannel.receiveAsFlow()

    var state by mutableStateOf(UiState())
        private set

    fun onUserNameChange(newUserName: String) {
        state = state.copy(username = newUserName)
    }

    fun onPasswordChange(newPassword: String) {
        state = state.copy(password = newPassword)
    }

    fun onLoginButtonClicked() {
        val errors = mutableListOf<String>()
        if (state.username.isBlank()) {
            errors.add("Missing username")
        }
        if (state.password.isBlank()) {
            errors.add("Missing password")
        }
        if (errors.isNotEmpty()) {
            state = state.copy(errorMessage = errors.joinToString(separator = "\n"))
        }

        performLogin(state.username, state.password)
    }

    fun onErrorReadButtonClick() {
        state = state.copy(errorMessage = "")
    }

    private fun performLogin(username: String, password: String) {
        state = state.copy(showLoading = true)
        screenModelScope.launch {
            sessionRepository.login(email = username, password = password)
                .onSuccess {
                    state = state.copy(showLoading = false)
                    navigationChannel.send(NavigationEvent.ToHome)
                }.onFailure { error ->
                    state = state.copy(
                        showLoading = false,
                        errorMessage = "Error: ${error.message}\n\n" + state.errorMessage
                    )
                }
        }
    }

    data class UiState(
        val username: String = "",
        val password: String = "",
        val errorMessage: String = "",
        val showLoading: Boolean = false
    )

    sealed interface NavigationEvent {
        data object ToHome : NavigationEvent
    }
}
