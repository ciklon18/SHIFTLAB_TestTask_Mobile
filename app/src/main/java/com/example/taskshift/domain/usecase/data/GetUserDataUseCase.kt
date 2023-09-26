package com.example.taskshift.domain.usecase.data

import com.example.taskshift.data.storage.UserDataStorage


class GetUserNameUseCase(private val userDataStorage: UserDataStorage) {
    fun execute(): String {
        return userDataStorage.getName()
    }
}