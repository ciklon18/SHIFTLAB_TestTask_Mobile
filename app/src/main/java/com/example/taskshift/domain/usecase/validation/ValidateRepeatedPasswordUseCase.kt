package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import com.example.taskshift.domain.model.ValidationResult

class ValidateRepeatedPasswordUseCase {
    fun execute(password: String, repeatedPassword: String): ValidationResult{
        if (repeatedPassword.isBlank()){
            return ValidationResult(false, ErrorMessageType.BLANK_REPEATED_PASSWORD)
        }
        if (password != repeatedPassword){
            return ValidationResult(
                false,
                ErrorMessageType.NOT_MATCHED_PASSWORDS
            )
        }
        return ValidationResult(true)
    }
}