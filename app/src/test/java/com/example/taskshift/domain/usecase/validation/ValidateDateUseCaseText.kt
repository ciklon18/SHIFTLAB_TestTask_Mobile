package com.example.taskshift.domain.usecase.validation

import com.example.taskshift.domain.model.ErrorMessageType
import org.junit.Test
import org.junit.Assert.*

class ValidateDateUseCaseTest {

    private val validateDateUseCase = ValidateDateUseCase()

    @Test
    fun `test blank date`() {
        val result = validateDateUseCase.execute("")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.BLANK_DATE, result.errorMessage)
    }

    @Test
    fun `test incorrect format date`() {
        val result = validateDateUseCase.execute("2021-09-26")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.INCORRECT_SYMBOL_DATE, result.errorMessage)
    }

    @Test
    fun `test incorrect date`() {
        val result = validateDateUseCase.execute("32.13.2021")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.INCORRECT_DATE, result.errorMessage)
    }

    @Test
    fun `test date with symbols`() {
        val result = validateDateUseCase.execute("2021@09@26")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.INCORRECT_SYMBOL_DATE, result.errorMessage)
    }

    @Test
    fun `test valid date`() {
        val result = validateDateUseCase.execute("26.09.2021")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }


    @Test
    fun `test date with two-digit day and month`() {
        val result = validateDateUseCase.execute("15.08.2023")
        assertTrue(result.successful)
        assertNull(result.errorMessage)
    }

    @Test
    fun `test date with four-digit year`() {
        val result = validateDateUseCase.execute("01.01.2030")
        assertFalse(result.successful)
        assertEquals(ErrorMessageType.INCORRECT_DATE, result.errorMessage)
    }
}
