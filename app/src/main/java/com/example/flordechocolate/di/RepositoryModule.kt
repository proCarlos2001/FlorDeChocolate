package com.example.flordechocolate.di

import com.example.flordechocolate.data.repositories.HomeRepository
import com.example.flordechocolate.data.repositories.LoginRepository
import org.koin.dsl.module

val repoModule = module {
    single { LoginRepository(get(), get(), get()) }
    single { HomeRepository(get(), get(), get(), get(), get()) }
}