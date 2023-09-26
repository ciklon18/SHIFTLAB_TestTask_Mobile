package com.example.taskshift.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)
