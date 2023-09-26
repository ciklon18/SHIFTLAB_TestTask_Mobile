package com.example.taskshift.domain.model

class ValidationPatterns {
    companion object {
        val firstUpperLetterRegex = Regex("^[A-ZА-Я][a-zа-яё]+\\s*\$")
        val dateCorrectRegex =
            Regex("^((0[1-9])|([12][0-9])|(3[0-1]))\\.((0[1-9])|(1[0-2]))\\.((19[0-9][0-9])|(20(([0-1][0-9])|(2[0-3]))))\$")
    }

}