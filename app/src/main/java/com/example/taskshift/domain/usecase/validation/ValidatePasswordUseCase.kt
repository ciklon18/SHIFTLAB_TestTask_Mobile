package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import com.example.taskshift.domain.model.ValidationResult

class ValidatePasswordUseCase {
    fun execute(password: String): ValidationResult {
        if (password.isBlank()){
            return ValidationResult(false, ErrorMessageType.BLANK_PASSWORD)
        }
        val containsLettersAndDigits = password.any { it.isDigit()} && password.any { it.isLetter() }

        if (!containsLettersAndDigits){
            return ValidationResult(false, ErrorMessageType.MISS_LETTER_OR_DIGIT)
        }
        return ValidationResult(true)
    }

}