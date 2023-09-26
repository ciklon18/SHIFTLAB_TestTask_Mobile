package com.example.taskshift.presentation.registrationforms

data class RegistrationFormState(
    val name: String = "",
    val nameError: Int? = null,
    val surname: String = "",
    val surnameError: Int? = null,
    val date: String = "",
    val dateError: Int? = null,
    val password: String = "",
    val passwordError: Int? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: Int? = null
)
