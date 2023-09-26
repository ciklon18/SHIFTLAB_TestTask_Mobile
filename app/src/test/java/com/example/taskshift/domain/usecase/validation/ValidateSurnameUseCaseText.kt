package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import org.junit.Test
import org.junit.Assert.*

class ValidateSurnameUseCaseTest {

    private val validateSurnameUseCase = ValidateSurnameUseCase()

    @Test
    fun `test blank surname`() {
        val result = validateSurnameUseCase.execute("")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.BLANK_SURNAME, result.errorMessage)
    }

    @Test
    fun `test short surname`() {
        val result = validateSurnameUseCase.execute("D")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.SHORT_SURNAME, result.errorMessage)
    }

    @Test
    fun `test surname with digits`() {
        val result = validateSurnameUseCase.execute("Smith123")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.DIGIT_SURNAME, result.errorMessage)
    }

    @Test
    fun `test surname with multiple uppercase letters`() {
        val result = validateSurnameUseCase.execute("SmithY")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.MANY_UPPER_LETTERS_SURNAME, result.errorMessage)
    }

    @Test
    fun `test valid surname`() {
        val result = validateSurnameUseCase.execute("Smith")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }
}
