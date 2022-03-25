package com.example.kumparanapp.core.di

import com.example.kumparanapp.common.utils.AppDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coreModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
}