package com.example.kumparanapp.feature.photo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val potoViewModel = module {
    viewModel {
        PhotoViewModel(get(), get())
    }
}