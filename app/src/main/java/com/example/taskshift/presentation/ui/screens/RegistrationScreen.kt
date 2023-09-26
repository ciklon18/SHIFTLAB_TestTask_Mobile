package com.example.taskshift.presentation.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskshift.R
import com.example.taskshift.domain.model.User
import com.example.taskshift.presentation.enums.ShiftScreenEnum
import com.example.taskshift.presentation.registrationforms.RegistrationFormEvent
import com.example.taskshift.presentation.ui.enums.FocusManagerAction
import com.example.taskshift.presentation.ui.textFields.PasswordOutlinedField
import com.example.taskshift.presentation.ui.textFields.StandardOutlinedField
import com.example.taskshift.presentation.ui.utils.isValid
import com.example.taskshift.presentation.ui.utils.showToast
import com.example.taskshift.presentation.viewmodel.RegistrationViewModel


@Composable
fun RegistrationPage(
    viewModel: RegistrationViewModel, navController: NavController, modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val state = viewModel.state
    val context = LocalContext.current

    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is RegistrationViewModel.ValidationEvent.Success -> {
                    showToast(context, R.string.succesful_registration)
                }
            }
        }
    }

    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(painter = painterResource(R.drawable.laboratory_icon), contentDescription = null)
        StandardOutlinedField(
            value = state.name,
            onValueChange = { viewModel.onEvent(RegistrationFormEvent.NameChanged(it)) },
            isError = state.nameError != null,
            stringRes = R.string.name_text,
            errorStringRes = state.nameError,
            imageVector = Icons.Filled.AccountCircle,
            focusManager = focusManager,
            modifier = Modifier.fillMaxWidth()
        )

        StandardOutlinedField(
            value = state.surname,
            onValueChange = { viewModel.onEvent(RegistrationFormEvent.SurnameChanged(it)) },
            isError = state.surnameError != null,
            stringRes = R.string.surname_text,
            errorStringRes = state.surnameError,
            imageVector = Icons.Filled.AccountCircle,
            focusManager = focusManager,
            modifier = Modifier.fillMaxWidth()
        )


        StandardOutlinedField(
            value = state.date,
            onValueChange = { viewModel.onEvent(RegistrationFormEvent.DateChanged(it)) },
            isError = state.dateError != null,
            stringRes = R.string.date_text,
            errorStringRes = state.dateError,
            imageVector = Icons.Filled.DateRange,
            focusManager = focusManager,
            modifier = Modifier.fillMaxWidth()
        )


        PasswordOutlinedField(
            value = state.password,
            onValueChange = { viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) },
            isError = state.passwordError != null,
            stringRes = R.string.password_text,
            errorStringRes = state.passwordError,
            imageVector = Icons.Filled.Lock,
            isPasswordVisible = passwordVisibility,
            isPasswordVisibilityToggle = { passwordVisibility = !passwordVisibility },
            focusManager = focusManager,
            focusManagerAction = FocusManagerAction.Next,
            modifier = Modifier.fillMaxWidth()
        )


        PasswordOutlinedField(
            value = state.repeatedPassword,
            onValueChange = { viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it)) },
            isError = state.repeatedPasswordError != null,
            stringRes = R.string.confirm_password_text,
            errorStringRes = state.repeatedPasswordError,
            isPasswordVisible = confirmPasswordVisibility,
            imageVector = Icons.Filled.Lock,
            isPasswordVisibilityToggle = { confirmPasswordVisibility = !confirmPasswordVisibility },
            focusManager = focusManager,
            focusManagerAction = FocusManagerAction.Exit,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = modifier.weight(0.2f))

2
        Button(
            onClick = {
                viewModel.onEvent(RegistrationFormEvent.Submit)
                if (isValid(viewModel.state)) {
                    val user = User(state.name, state.surname, state.date, state.password)
                    viewModel.saveUserData(user)

                    navController.navigate(ShiftScreenEnum.Greeting.name) {
                        popUpTo(ShiftScreenEnum.Registration.name) {
                            inclusive = true
                        }
                    }
                } else {
                    showToast(context, R.string.validation_error_message)
                }
            }, modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)

        ) {
            ButtonText()
        }
        Spacer(modifier = modifier.weight(0.1f))
    }
}

@Composable
fun ButtonText() {
    Text(
        text = stringResource(R.string.confirm_registration),
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 22.sp,
        lineHeight = 26.sp,
    )
}

