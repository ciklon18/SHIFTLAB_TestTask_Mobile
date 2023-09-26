package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import com.example.taskshift.domain.model.ValidationPatterns.Companion.firstUpperLetterRegex
import com.example.taskshift.domain.model.ValidationResult

class ValidateNameUseCase {
    fun execute(name: String): ValidationResult {
        return if (name.isBlank()) {
            ValidationResult(false, ErrorMessageType.BLANK_NAME)
        } else if (name.length < 2) {
            ValidationResult(false, ErrorMessageType.SHORT_NAME)
        } else if (name.any { it.isDigit() }) {
            ValidationResult(false, ErrorMessageType.DIGIT_NAME)
        } else if (!firstUpperLetterRegex.matches(name) || name.substring(1).any { it.isUpperCase() }) {
            ValidationResult(false, ErrorMessageType.MANY_UPPER_LETTERS_NAME)
        } else {
            ValidationResult(true)
        }
    }
}