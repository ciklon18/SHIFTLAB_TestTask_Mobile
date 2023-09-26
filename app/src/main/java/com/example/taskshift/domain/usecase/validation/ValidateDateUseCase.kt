package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import com.example.taskshift.domain.model.ValidationPatterns.Companion.dateCorrectRegex
import com.example.taskshift.domain.model.ValidationResult

class ValidateDateUseCase {
    fun execute(date: String): ValidationResult {
        return if (date.isBlank()) {
            ValidationResult(false, ErrorMessageType.BLANK_DATE)
        } else if (date.any { !(it.isDigit() || it == '.') }) {
            ValidationResult(false, ErrorMessageType.INCORRECT_SYMBOL_DATE)
        } else if (!dateCorrectRegex.matches(date)) {
            val dateElements = date.split(".")
            if (dateElements.size != 3) {
                ValidationResult(false, ErrorMessageType.INCORRECT_FORMAT_DATE)
            } else {
                ValidationResult(false, ErrorMessageType.INCORRECT_DATE)
            }
        } else {
            ValidationResult(true)
        }
    }
}