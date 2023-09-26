package com.example.taskshift.domain.usecase.data

import com.example.taskshift.data.storage.UserDataStorage
import com.example.taskshift.domain.model.User


class SaveUserDataUseCase(private val userDataStorage: UserDataStorage) {
    fun execute(user: User){
        userDataStorage.saveData(user)
    }
}