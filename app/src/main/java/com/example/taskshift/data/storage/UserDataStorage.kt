package com.example.taskshift.data.storage

import com.example.taskshift.domain.model.User

interface UserDataStorage {
    fun saveData(user: User)
    fun getName(): String
}