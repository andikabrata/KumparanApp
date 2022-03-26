package com.example.kumparanapp.feature.post.di

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
import com.example.kumparanapp.core.db.entity.PostEntity
import com.example.kumparanapp.core.db.entity.UserEntity
import com.example.kumparanapp.core.db.entity.UserMirrorEntity
import com.example.kumparanapp.model.post.Posts
import com.example.kumparanapp.model.user.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPostViewModel(
    private val serviceUtil: ServiceApi,
    private val dispatchers: AppDispatchers,
    private val daoProvider: DaoProvider
) : BaseViewModel() {
    val listUserLiveData = MutableLiveData<ViewState<List<User>>>()
    val listPostLiveData = MutableLiveData<ViewState<List<Posts>>>()
    var coba: String? = null

    fun getUser() {
        listUserLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getUser()
                listUserLiveData.setSuccess(result)
            },
            onError = {
                listUserLiveData.setError(it)
            }
        )
    }

    fun getListPost() {
        listPostLiveData.setLoading()
        launchOnUi(
            dispatcher = dispatchers,
            onRequest = {
                val result = serviceUtil.getPost()
                listPostLiveData.setSuccess(result)
            },
            onError = {
                listPostLiveData.setError(it)
            }
        )
    }

    fun insertUserDb(listUser: List<User>) {
        CoroutineScope(Dispatchers.IO).launch {
            daoProvider.userDao.deleteAll()
            listUser.forEach {
                daoProvider.userDao.insert(
                    UserEntity(
                        Id = it.id,
                        Name = it.name,
                        Username = it.username,
                        Email = it.email,
                        Street = it.address.street,
                        City = it.address.city,
                        Zipcode = it.address.zipcode,
                        Lat = it.address.geo.lat,
                        Lng = it.address.geo.lng,
                        Phone = it.phone,
                        Website = it.website,
                        CompanyName = it.company.name,
                        CatchPhrase = it.company.catchPhrase,
                        Bs = it.company.bs
                    )
                )
            }
        }
    }

    fun insertPostDb(listUser: List<Posts>) {
        CoroutineScope(Dispatchers.IO).launch {
            daoProvider.postDao.deleteAll()
            listUser.forEach {
                daoProvider.postDao.insert(
                    PostEntity(
                        userID = it.userId,
                        Id = it.id,
                        Title = it.title,
                        Body = it.body
                    )
                )
            }
        }
    }

    fun getUserById(id_user: String?): LiveData<List<UserEntity>> = if (id_user.isNullOrEmpty()) {
        daoProvider.userDao.getAll()
    } else {
        daoProvider.userDao.getUserById(id_user)
    }

    fun insertUserMirrorDb(listUser: List<UserEntity>) {
        CoroutineScope(Dispatchers.IO).launch {
            listUser.forEach {
                daoProvider.userMirrorDao.insert(
                    UserMirrorEntity(
                        Id = it.Id,
                        Name = it.Name,
                        Username = it.Username,
                        Email = it.Email,
                        Street = it.Street,
                        City = it.City,
                        Zipcode = it.Zipcode,
                        Lat = it.Lat,
                        Lng = it.Lng,
                        Phone = it.Phone,
                        Website = it.Website,
                        CompanyName = it.Name,
                        CatchPhrase = it.CatchPhrase,
                        Bs = it.Bs
                    )
                )
            }
        }
    }

    fun deleteUserMirror() {
        CoroutineScope(Dispatchers.IO).launch {
            daoProvider.userMirrorDao.deleteAll()
        }
    }

    fun getUserMirrorById(id_user: String?): LiveData<List<UserMirrorEntity>> = if (id_user.isNullOrEmpty()) {
        daoProvider.userMirrorDao.getAll()
    } else {
        daoProvider.userMirrorDao.getUserById(id_user)
    }
}