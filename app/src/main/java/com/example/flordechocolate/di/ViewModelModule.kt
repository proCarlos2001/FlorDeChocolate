package com.example.flordechocolate.di

import com.example.flordechocolate.data.viewmodels.HomeViewModel
import com.example.flordechocolate.data.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }
}