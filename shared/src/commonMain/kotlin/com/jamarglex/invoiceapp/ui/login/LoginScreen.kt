package com.jamarglex.invoiceapp.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jamarglex.invoiceapp.shared.strings.ResStrings
import com.jamarglex.invoiceapp.ui.home.HomeScreen
import kotlinx.coroutines.delay

internal object LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val loginViewModel = getScreenModel<LoginViewModel>()

        LaunchedEffect(Unit) {
            loginViewModel.navigationEvents.collect { destination ->
                when (destination) {
                    LoginViewModel.NavigationEvent.ToHome -> navigator.push(HomeScreen)
                }
            }
        }

        LoginScreenContent(
            uiState = loginViewModel.state,
            onUserNameChanged = loginViewModel::onUserNameChange,
            onPasswordChanged = loginViewModel::onPasswordChange,
            onLoginClicked = loginViewModel::onLoginButtonClicked,
            onErrorReadButtonClick = loginViewModel::onErrorReadButtonClick
        )
    }

}


@Composable
internal fun LoginScreenContent(
    uiState: LoginViewModel.UiState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onErrorReadButtonClick: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        delay(200)
        focusRequester.requestFocus()
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (uiState.showLoading) {
            CircularProgressIndicator()
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.focusRequester(focusRequester),
                value = uiState.username,
                onValueChange = onUserNameChanged,
                label = { Text(ResStrings.login_screen_username_label) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.password,
                onValueChange = onPasswordChanged,
                label = { Text(ResStrings.login_screen_password_label) },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLoginClicked
            ) {
                Text(ResStrings.login_screen_login_button_label)
            }

            if (uiState.errorMessage.isNotBlank()) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = uiState.errorMessage,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = onErrorReadButtonClick
                ) {
                    Text(ResStrings.login_screen_error_read_button_label)
                }
            }
        }
    }
}
