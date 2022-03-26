package com.example.kumparanapp.feature.postdetail.di

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

class ListDetailPostViewModel(
    private val serviceUtil: ServiceApi,
    private val dispatchers: AppDispatchers,
    private val daoProvider: DaoProvider
) : BaseViewModel() {

    val listCommentsLiveData = MutableLiveData<ViewState<List<Comments>>>()

    fun getComment(id: String) {
        listCommentsLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getListComment(id)
                listCommentsLiveData.setSuccess(result)
            },
            onError = {
                listCommentsLiveData.setError(it)
            }
        )
    }

    fun getUserById(id_user: String?): LiveData<List<UserEntity>> = if (id_user.isNullOrEmpty()) {
        daoProvider.userDao.getAll()
    } else {
        daoProvider.userDao.getUserById(id_user)
    }
}