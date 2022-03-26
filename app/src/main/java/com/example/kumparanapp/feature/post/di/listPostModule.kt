package com.example.kumparanapp.feature.post.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listPostViewModel = module {
    viewModel {
        ListPostViewModel(get(), get(), get())
    }
}