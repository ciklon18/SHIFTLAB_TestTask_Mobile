package com.example.taskshift.di

import com.example.taskshift.domain.usecase.data.GetUserNameUseCase
import com.example.taskshift.domain.usecase.data.SaveUserDataUseCase
import com.example.taskshift.domain.usecase.validation.ValidateDateUseCase
import com.example.taskshift.domain.usecase.validation.ValidateNameUseCase
import com.example.taskshift.domain.usecase.validation.ValidatePasswordUseCase
import com.example.taskshift.domain.usecase.validation.ValidateRepeatedPasswordUseCase
import com.example.taskshift.domain.usecase.validation.ValidateSurnameUseCase
import org.koin.dsl.module


val domainModule = module {
    single {
        GetUserNameUseCase(userDataStorage = get())
    }
    single {
        SaveUserDataUseCase(userDataStorage = get())
    }
    single {
        ValidateNameUseCase()
    }
    single {
        ValidateSurnameUseCase()
    }
    single {
        ValidateDateUseCase()
    }
    single {
        ValidatePasswordUseCase()
    }
    single {
        ValidateRepeatedPasswordUseCase()
    }
}