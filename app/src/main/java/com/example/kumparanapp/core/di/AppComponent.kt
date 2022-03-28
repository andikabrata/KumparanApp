package com.example.kumparanapp.core.di

import com.example.kumparanapp.feature.photo.di.potoViewModel
import com.example.kumparanapp.feature.post.di.listPostViewModel
import com.example.kumparanapp.feature.postdetail.di.listDetailPostViewModel
import com.example.kumparanapp.feature.userdetail.di.detailUserViewModel


/**
 * List of modules
 */
val appComponent = listOf(
    coreModule,
    databaseModule,
    retrofitModule,
    listPostViewModel,
    listDetailPostViewModel,
    detailUserViewModel,
    potoViewModel
)
