package com.example.taskshift.data.storage

import android.content.SharedPreferences
import com.example.taskshift.domain.model.User

class UserStorage(private val sharedPreferences: SharedPreferences) : UserDataStorage {
    override fun saveData(user: User) {
        with(sharedPreferences.edit()) {
            putString("name", user.name)
            putString("surname", user.surname)
            putString("date", user.date)
            putString("password", user.password)

            apply()
        }
    }

    override fun getName(): String {
        val name = sharedPreferences.getString("name", "") ?: ""
        val surname = sharedPreferences.getString("surname", "") ?: ""

        return "$name $surname"
    }

}