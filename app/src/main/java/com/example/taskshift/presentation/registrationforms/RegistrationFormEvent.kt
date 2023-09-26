package com.example.taskshift.presentation.registrationforms

sealed class RegistrationFormEvent {
    data class NameChanged(val name: String): RegistrationFormEvent()
    data class SurnameChanged(val surname: String): RegistrationFormEvent()
    data class DateChanged(val date: String): RegistrationFormEvent()
    data class PasswordChanged(val password: String): RegistrationFormEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String): RegistrationFormEvent()

    object Submit: RegistrationFormEvent()
}
