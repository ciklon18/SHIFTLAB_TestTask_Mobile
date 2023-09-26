package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import com.example.taskshift.domain.model.ValidationPatterns
import com.example.taskshift.domain.model.ValidationResult

class ValidateSurnameUseCase {
    fun execute(surname: String): ValidationResult {
        return if (surname.isBlank()) {
            ValidationResult(false, ErrorMessageType.BLANK_SURNAME)
        } else if (surname.length < 2) {
            ValidationResult(false, ErrorMessageType.SHORT_SURNAME)
        } else if (surname.any { it.isDigit() }) {
            ValidationResult(false, ErrorMessageType.DIGIT_SURNAME)
        } else if (!ValidationPatterns.firstUpperLetterRegex.matches(surname)
            || surname.substring(1).any { it.isUpperCase() }
        ) {
            ValidationResult(false, ErrorMessageType.MANY_UPPER_LETTERS_SURNAME)
        } else {
            ValidationResult(true)
        }

    }
}