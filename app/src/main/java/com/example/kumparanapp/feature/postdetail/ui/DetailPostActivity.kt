package com.example.kumparanapp.feature.postdetail.ui

import android.os.Bundle
import com.example.kumparanapp.common.extension.*
import com.example.kumparanapp.core.base.BaseVMActivity
import com.example.kumparanapp.databinding.ActivityDetailPostBinding
import com.example.kumparanapp.feature.postdetail.adapter.DetailPostAdapter
import com.example.kumparanapp.feature.postdetail.di.ListDetailPostViewModel
import com.example.kumparanapp.feature.userdetail.ui.DetailUserActivity
import kotlin.text.orEmpty

class DetailPostActivity : BaseVMActivity<ListDetailPostViewModel, ActivityDetailPostBinding>() {
    private var id: String = ""
    private var userId: String = ""
    private var title: String = ""
    private var body: String = ""

    private val adapter by lazy {
        DetailPostAdapter() {
            snackBar("COOMING SOON")
        }
    }

    override fun getViewModel() = ListDetailPostViewModel::class.java

    override fun getViewBinding(): ActivityDetailPostBinding {
        return ActivityDetailPostBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        id = intent.getStringExtra("id").orEmpty()
        userId = intent.getStringExtra("user_id").orEmpty()
        title = intent.getStringExtra("title").orEmpty()
        body = intent.getStringExtra("body").orEmpty()

        initToolbar(binding.toolbar, "Detail Post", back = true)

        viewModel.getUserById(userId)
            .observe(this) { list ->
                binding.tvUsername.text = list[0].Username
                binding.tvCompanyName.text = list[0].CompanyName
            }

        binding.tvTitle.text = title
        binding.tvBody.text = body

        viewModel.getComment(id)

        binding.listComment.apply {
            gridLayoutManager(1)
        }

        binding.linearDetailUser.setOnClickListener {
            startActivity<DetailUserActivity> {
                putExtra("user_id", userId)
            }
        }
    }

    override fun observerViewModel() {
        viewModel.listCommentsLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.stateLayout.toLoading()
                }

                is ViewState.Success -> {
                    if (state.data.isNotEmpty().orFalse()) {
                        binding.stateLayout.toContent()
                        state.data.let { adapter.addData(it) }
                        binding.listComment.adapter = adapter
                    } else
                        binding.stateLayout.toEmpty()
                }

                is ViewState.Failed -> {
                    binding.stateLayout.toError {
                        viewModel.getComment(id)
                    }
                }
            }
        }
    }
}