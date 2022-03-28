package com.example.kumparanapp.feature.post.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.kumparanapp.common.extension.*
import com.example.kumparanapp.core.base.BaseVMActivity
import com.example.kumparanapp.core.db.entity.UserEntity
import com.example.kumparanapp.core.db.entity.UserMirrorEntity
import com.example.kumparanapp.databinding.ActivityPostBinding
import com.example.kumparanapp.feature.post.adapter.PostAdapter
import com.example.kumparanapp.feature.post.di.ListPostViewModel
import com.example.kumparanapp.feature.postdetail.ui.DetailPostActivity

class PostActivity : BaseVMActivity<ListPostViewModel, ActivityPostBinding>() {

    private var listUser: ArrayList<UserEntity> = arrayListOf()
    private var listUserMirror: ArrayList<UserMirrorEntity> = arrayListOf()
    private lateinit var mainHandler: Handler

    private val adapter by lazy {
        PostAdapter() {
            startActivity<DetailPostActivity> {
                putExtra("id",it.id.toString())
                putExtra("user_id",it.userId.toString())
                putExtra("title",it.title)
                putExtra("body",it.body)
            }
        }
    }

    override fun getViewModel() = ListPostViewModel::class.java

    override fun getViewBinding(): ActivityPostBinding {
        return ActivityPostBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initToolbar(binding.toolbar, "All Post", back = false)

        mainHandler = Handler(Looper.getMainLooper())

        viewModel.getUser()

        viewModel.getListPost()

        binding.listPost.apply {
            gridLayoutManager(1)
        }

    }

    override fun observerViewModel() {
        viewModel.listUserLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.stateLayout.toLoading()
                }

                is ViewState.Success -> {
                    if (state.data.isNotEmpty().orFalse()) {
                        binding.stateLayout.toContent()
                        viewModel.insertUserDb(state.data)
                    } else
                        binding.stateLayout.toEmpty()
                }

                is ViewState.Failed -> {
                    binding.stateLayout.toError {
                        viewModel.getUser()
                    }
                }
            }
        }

        viewModel.listPostLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.stateLayout.toLoading()
                }

                is ViewState.Success -> {
                    if (state.data.isNotEmpty().orFalse()) {
                        binding.stateLayout.toContent()

                        viewModel.insertPostDb(state.data)

                        viewModel.deleteUserMirror()

                        for (i in state.data.indices) {
                            mainHandler.postDelayed({
                                viewModel.getUserById(state.data[i].userId.toString())
                                    .observe(this) { list ->
                                        listUser.clear()
                                        listUser.addAll(list)
                                        viewModel.insertUserMirrorDb(list)
                                    }
                            }, 1000)
                        }

                        viewModel.getUserMirrorById("").observe(this) { list ->
                            listUserMirror.clear()
                            listUserMirror.addAll(list)
                            if (list.isNotEmpty()) {
                                binding.stateLayout.toContent()
                                state.data.let { adapter.addData(it, listUserMirror) }
                                binding.listPost.adapter = adapter
                            } else {
                                binding.stateLayout.toLoading()
                            }
                        }

                    } else
                        binding.stateLayout.toEmpty()
                }

                is ViewState.Failed -> {
                    binding.stateLayout.toError {
                        viewModel.getListPost()
                    }
                }
            }
        }
    }
}