package com.example.taskshift.domain.model

import com.example.taskshift.R

class ErrorMessageType {
    companion object {
        val BLANK_NAME = R.string.blank_name_error
        val SHORT_NAME = R.string.short_name
        val DIGIT_NAME = R.string.digit_name
        val MANY_UPPER_LETTERS_NAME = R.string.many_upper_letters_name

        val BLANK_SURNAME = R.string.blank_surname
        val SHORT_SURNAME = R.string.short_surname
        val DIGIT_SURNAME = R.string.digit_surname
        val MANY_UPPER_LETTERS_SURNAME = R.string.many_upper_letters_surname

        val BLANK_DATE = R.string.blank_date
        val INCORRECT_FORMAT_DATE = R.string.incorrect_format_date
        val INCORRECT_DATE = R.string.incorrect_date
        val INCORRECT_SYMBOL_DATE = R.string.incorrect_symbol_date

        val BLANK_PASSWORD = R.string.blank_password
        val MISS_LETTER_OR_DIGIT = R.string.miss_letter_or_digit

        val BLANK_REPEATED_PASSWORD = R.string.blank_repeated_password
        val NOT_MATCHED_PASSWORDS = R.string.not_matched_passwords
    }
}
