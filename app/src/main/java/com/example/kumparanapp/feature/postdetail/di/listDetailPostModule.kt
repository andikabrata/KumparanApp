package com.example.kumparanapp.feature.postdetail.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listDetailPostViewModel = module {
    viewModel {
        ListDetailPostViewModel(get(), get(), get())
    }
}