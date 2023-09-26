package com.example.taskshift.di

import com.example.taskshift.presentation.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel() {
        RegistrationViewModel(
            saveUserDataUseCase = get(),
            getUserNameUseCase = get(),
            validateNameUseCase = get(),
            validateSurnameUseCase = get(),
            validateDateUseCase = get(),
            validatePasswordUseCase = get(),
            validateRepeatedPasswordUseCase = get()
        )
    }
}