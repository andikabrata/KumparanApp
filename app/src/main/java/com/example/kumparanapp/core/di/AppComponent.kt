package com.example.kumparanapp.core.di

import com.example.kumparanapp.feature.post.di.listPostViewModel


/**
 * List of modules
 */
val appComponent = listOf(
    coreModule,
    databaseModule,
    retrofitModule,
    listPostViewModel
)
