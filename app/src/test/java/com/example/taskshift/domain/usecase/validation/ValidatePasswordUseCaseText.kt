package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import org.junit.Test
import org.junit.Assert.*

class ValidatePasswordUseCaseTest {

    private val validatePasswordUseCase = ValidatePasswordUseCase()

    @Test
    fun `test blank password`() {
        val result = validatePasswordUseCase.execute("")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.BLANK_PASSWORD, result.errorMessage)
    }

    @Test
    fun `test password without letters`() {
        val result = validatePasswordUseCase.execute("123456")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.MISS_LETTER_OR_DIGIT, result.errorMessage)
    }
    @Test
    fun `text password without letters and digits`(){
        val result = validatePasswordUseCase.execute("@$$$%@@%^.")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.MISS_LETTER_OR_DIGIT, result.errorMessage)
    }

    @Test
    fun `test password without digits`() {
        val result = validatePasswordUseCase.execute("Password")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.MISS_LETTER_OR_DIGIT, result.errorMessage)
    }

    @Test
    fun `test valid password`() {
        val result = validatePasswordUseCase.execute("P@ssw0rd")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }
    @Test
    fun `test password with more than 20 characters`() {
        val result = validatePasswordUseCase.execute("VeryLongPasswordWithSpecialCharacters123")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }
}
