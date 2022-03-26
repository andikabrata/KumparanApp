package com.example.kumparanapp.core.di

import com.example.kumparanapp.feature.post.di.listPostViewModel
import com.example.kumparanapp.feature.postdetail.di.listDetailPostViewModel


/**
 * List of modules
 */
val appComponent = listOf(
    coreModule,
    databaseModule,
    retrofitModule,
    listPostViewModel,
    listDetailPostViewModel
)
