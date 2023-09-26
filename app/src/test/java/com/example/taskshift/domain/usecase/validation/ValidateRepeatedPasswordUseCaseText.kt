package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import org.junit.Test
import org.junit.Assert.*

class ValidateRepeatedPasswordUseCaseTest {

    private val validateRepeatedPasswordUseCase = ValidateRepeatedPasswordUseCase()

    @Test
    fun `test blank repeated password`() {
        val result = validateRepeatedPasswordUseCase.execute("Password", "")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.BLANK_REPEATED_PASSWORD, result.errorMessage)
    }

    @Test
    fun `test non-matching passwords`() {
        val result = validateRepeatedPasswordUseCase.execute("Password", "Mismatched")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.NOT_MATCHED_PASSWORDS, result.errorMessage)
    }

    @Test
    fun `test matching passwords`() {
        val result = validateRepeatedPasswordUseCase.execute("Password", "Password")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }
}
