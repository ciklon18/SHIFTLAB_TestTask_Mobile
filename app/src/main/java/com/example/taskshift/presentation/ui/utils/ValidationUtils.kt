package com.example.taskshift.presentation.ui.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.taskshift.presentation.registrationforms.RegistrationFormState

fun isValid(state: RegistrationFormState): Boolean {
    return state.nameError == null &&
            state.surnameError == null &&
            state.dateError == null &&
            state.passwordError == null &&
            state.repeatedPasswordError == null
}

fun showToast(context: Context, @StringRes messageRes: Int) {
    Toast.makeText(
        context,
        messageRes,
        Toast.LENGTH_SHORT
    ).show()
}