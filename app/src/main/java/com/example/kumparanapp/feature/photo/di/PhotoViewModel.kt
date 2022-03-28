package com.example.kumparanapp.feature.photo.di

import androidx.lifecycle.MutableLiveData
import com.example.kumparanapp.common.extension.ViewState
import com.example.kumparanapp.common.extension.setError
import com.example.kumparanapp.common.extension.setLoading
import com.example.kumparanapp.common.extension.setSuccess
import com.example.kumparanapp.common.utils.AppDispatchers
import com.example.kumparanapp.core.api.ServiceApi
import com.example.kumparanapp.core.base.BaseViewModel
import com.example.kumparanapp.model.photo.Photo

class PhotoViewModel(
    private val serviceUtil: ServiceApi,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    val listPhotoLiveData = MutableLiveData<ViewState<List<Photo>>>()

    fun getPhoto(albumId: String) {
        listPhotoLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getPhoto(albumId)
                listPhotoLiveData.setSuccess(result)
            },
            onError = {
                listPhotoLiveData.setError(it)
            }
        )
    }
}