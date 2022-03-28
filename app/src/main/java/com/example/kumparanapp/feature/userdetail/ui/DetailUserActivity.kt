package com.example.kumparanapp.feature.userdetail.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kumparanapp.common.extension.*
import com.example.kumparanapp.core.base.BaseVMActivity
import com.example.kumparanapp.databinding.ActivityDetailPostBinding
import com.example.kumparanapp.databinding.ActivityDetailUserBinding
import com.example.kumparanapp.feature.photo.ui.PhotoActivity
import com.example.kumparanapp.feature.postdetail.adapter.DetailPostAdapter
import com.example.kumparanapp.feature.postdetail.di.ListDetailPostViewModel
import com.example.kumparanapp.feature.userdetail.adapter.DetailUserAdapter
import com.example.kumparanapp.feature.userdetail.di.DetailUserViewModel
import kotlin.text.orEmpty

class DetailUserActivity : BaseVMActivity<DetailUserViewModel, ActivityDetailUserBinding>() {
    private var userId: String = ""

    private val adapter by lazy {
        DetailUserAdapter() {
            startActivity<PhotoActivity> {
                putExtra("id",it.id.toString())
            }
        }
    }

    override fun getViewModel() = DetailUserViewModel::class.java

    override fun getViewBinding() : ActivityDetailUserBinding {
        return ActivityDetailUserBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initToolbar(binding.toolbar, "Detail User", back = true)

        userId = intent.getStringExtra("user_id").orEmpty()

        viewModel.getUserById(userId)
            .observe(this) { list ->
                binding.tvUsername.text = list[0].Username
                binding.tvCompanyName.text = list[0].CompanyName
                binding.tvEmail.text = list[0].Email
                binding.tvStreet.text = list[0].Street
                binding.tvSuite.text = list[0].Suite
                binding.tvCity.text = list[0].City
                binding.tvZipcode.text = list[0].Zipcode
            }

        binding.listAlbum.apply {
            linearLayoutManager(LinearLayoutManager.HORIZONTAL)
        }

        viewModel.getAlbum(userId)
    }

    override fun observerViewModel() {

        viewModel.listAlbumUserLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.stateLayout.toLoading()
                }

                is ViewState.Success -> {
                    if (state.data.isNotEmpty().orFalse()) {
                        binding.stateLayout.toContent()
                        state.data.let { adapter.addData(it) }
                        binding.listAlbum.adapter = adapter
                    } else
                        binding.stateLayout.toEmpty()
                }

                is ViewState.Failed -> {
                    binding.stateLayout.toError {
                        viewModel.getAlbum(userId)
                    }
                }
            }
        }

    }

}