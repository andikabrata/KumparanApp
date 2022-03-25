package com.example.kumparanapp.common.utils

import kotlinx.coroutines.CoroutineDispatcher


class AppDispatchers(val main: CoroutineDispatcher,
                     val io: CoroutineDispatcher)