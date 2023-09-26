package com.example.taskshift.di

import android.content.Context
import com.example.taskshift.data.storage.UserDataStorage
import com.example.taskshift.data.storage.UserStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataModule = module {
    factory {
        androidContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }
    single<UserDataStorage> {
        UserStorage(sharedPreferences = get())
    }
}