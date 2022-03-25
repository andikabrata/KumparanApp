package com.example.kumparanapp.common.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kumparanapp.common.utils.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


inline fun ViewModel.runOnUi(crossinline block: suspend (CoroutineScope.() -> Unit)): Job {
    return viewModelScope.launch(Dispatchers.Main) {
        block()
    }
}

inline fun ViewModel.runOnUi(
    dispatcher: AppDispatchers,
    crossinline block: suspend (CoroutineScope.() -> Unit)
): Job {
    return viewModelScope.launch(dispatcher.main) {
        block()
    }
}