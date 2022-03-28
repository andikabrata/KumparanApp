package com.example.kumparanapp.feature.photo.ui

import android.os.Bundle
import com.example.kumparanapp.common.extension.*
import com.example.kumparanapp.core.base.BaseVMActivity
import com.example.kumparanapp.databinding.ActivityPhotoBinding
import com.example.kumparanapp.feature.photo.adapter.PhotoAdapter
import com.example.kumparanapp.feature.photo.di.PhotoViewModel

class PhotoActivity : BaseVMActivity<PhotoViewModel, ActivityPhotoBinding>() {

    private var Id: String = ""

    private val adapter by lazy {
        PhotoAdapter() {
            startActivity<PhotoViewActivity> {
                putExtra("albumId",it.albumId.toString())
                putExtra("id",it.id.toString())
                putExtra("title",it.title)
                putExtra("url",it.url)
                putExtra("thumbnailUrl",it.thumbnailUrl)
            }
//            snackBar("COMING SOON")
        }
    }

    override fun getViewModel() = PhotoViewModel::class.java

    override fun getViewBinding(): ActivityPhotoBinding {
        return ActivityPhotoBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initToolbar(binding.toolbar, "Photo", back = true)

        Id = intent.getStringExtra("id").orEmpty()

        viewModel.getPhoto(Id)

        binding.listPhoto.apply {
            gridLayoutManager(3)
        }
    }

    override fun observerViewModel() {
        viewModel.listPhotoLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.stateLayout.toLoading()
                }

                is ViewState.Success -> {
                    if (state.data.isNotEmpty().orFalse()) {
                        binding.stateLayout.toContent()
                        state.data.let { adapter.addData(it) }
                        binding.listPhoto.adapter = adapter
                    } else
                        binding.stateLayout.toEmpty()
                }

                is ViewState.Failed -> {
                    binding.stateLayout.toError {
                        viewModel.getPhoto(Id)
                    }
                }
            }
        }
    }

}