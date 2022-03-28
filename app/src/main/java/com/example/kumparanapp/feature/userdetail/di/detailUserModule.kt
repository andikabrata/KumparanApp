package com.example.kumparanapp.feature.userdetail.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailUserViewModel = module {
    viewModel {
        DetailUserViewModel(get(), get(), get())
    }
}