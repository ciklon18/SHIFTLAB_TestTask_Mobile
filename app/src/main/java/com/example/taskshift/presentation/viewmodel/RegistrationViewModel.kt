package com.example.taskshift.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskshift.domain.model.User
import com.example.taskshift.domain.usecase.data.GetUserNameUseCase
import com.example.taskshift.domain.usecase.data.SaveUserDataUseCase
import com.example.taskshift.domain.usecase.validation.ValidateDateUseCase
import com.example.taskshift.domain.usecase.validation.ValidateNameUseCase
import com.example.taskshift.domain.usecase.validation.ValidatePasswordUseCase
import com.example.taskshift.domain.usecase.validation.ValidateRepeatedPasswordUseCase
import com.example.taskshift.domain.usecase.validation.ValidateSurnameUseCase
import com.example.taskshift.presentation.registrationforms.RegistrationFormEvent
import com.example.taskshift.presentation.registrationforms.RegistrationFormState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val validateSurnameUseCase: ValidateSurnameUseCase,
    private val validateDateUseCase: ValidateDateUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase
) : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }

            is RegistrationFormEvent.SurnameChanged -> {
                state = state.copy(surname = event.surname)
            }

            is RegistrationFormEvent.DateChanged -> {
                state = state.copy(date = event.date)
            }

            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val nameResult = validateNameUseCase.execute(state.name)
        val surnameResult = validateSurnameUseCase.execute(state.surname)
        val dateResult = validateDateUseCase.execute(state.date)
        val passwordResult = validatePasswordUseCase.execute(state.password)
        val repeatedPasswordResult =
            validateRepeatedPasswordUseCase.execute(state.password, state.repeatedPassword)

        state = state.copy(
            nameError = nameResult.errorMessage,
            surnameError = surnameResult.errorMessage,
            dateError = dateResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            repeatedPasswordError = repeatedPasswordResult.errorMessage
        )
        state = state.copy(
            nameError = nameResult.errorMessage,
            surnameError = surnameResult.errorMessage,
            dateError = dateResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            repeatedPasswordError = repeatedPasswordResult.errorMessage
        )

        val hasError = listOf(
            nameResult, surnameResult, dateResult, passwordResult, repeatedPasswordResult
        ).any { !it.successful }

        if (hasError) {
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }

    fun saveUserData(user: User) {
        saveUserDataUseCase.execute(user)
    }

    fun getUserName(): String {
        return getUserNameUseCase.execute()
    }
}