package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import org.junit.Test
import org.junit.Assert.*

class ValidateNameUseCaseTest {

    private val validateNameUseCase = ValidateNameUseCase()

    @Test
    fun `test blank name`() {
        val result = validateNameUseCase.execute("")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.BLANK_NAME, result.errorMessage)
    }

    @Test
    fun `test short name`() {
        val result = validateNameUseCase.execute("A")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.SHORT_NAME, result.errorMessage)
    }

    @Test
    fun `test name with digits`() {
        val result = validateNameUseCase.execute("John123")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.DIGIT_NAME, result.errorMessage)
    }

    @Test
    fun `test name with multiple uppercase letters`() {
        val result = validateNameUseCase.execute("JoHn")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.MANY_UPPER_LETTERS_NAME, result.errorMessage)
    }

    @Test
    fun `test valid name`() {
        val result = validateNameUseCase.execute("John")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }
}
