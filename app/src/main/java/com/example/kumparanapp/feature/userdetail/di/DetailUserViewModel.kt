package com.example.kumparanapp.feature.userdetail.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kumparanapp.common.extension.ViewState
import com.example.kumparanapp.common.extension.setError
import com.example.kumparanapp.common.extension.setLoading
import com.example.kumparanapp.common.extension.setSuccess
import com.example.kumparanapp.common.utils.AppDispatchers
import com.example.kumparanapp.core.api.ServiceApi
import com.example.kumparanapp.core.base.BaseViewModel
import com.example.kumparanapp.core.db.DaoProvider
import com.example.kumparanapp.core.db.entity.UserEntity
import com.example.kumparanapp.model.postdetail.Comments
import com.example.kumparanapp.model.userdetail.Album

class DetailUserViewModel(
    private val serviceUtil: ServiceApi,
    private val dispatchers: AppDispatchers,
    private val daoProvider: DaoProvider
) : BaseViewModel() {

    val listAlbumUserLiveData = MutableLiveData<ViewState<List<Album>>>()

    fun getAlbum(userId: String) {
        listAlbumUserLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getAlbum(userId)
                listAlbumUserLiveData.setSuccess(result)
            },
            onError = {
                listAlbumUserLiveData.setError(it)
            }
        )
    }

    fun getUserById(id_user: String?): LiveData<List<UserEntity>> = if (id_user.isNullOrEmpty()) {
        daoProvider.userDao.getAll()
    } else {
        daoProvider.userDao.getUserById(id_user)
    }

}