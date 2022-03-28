package com.example.kumparanapp.feature.photo.ui

import android.os.Bundle
import com.example.kumparanapp.common.extension.orEmpty
import com.example.kumparanapp.core.base.BaseActivity
import com.example.kumparanapp.databinding.ActivityPhotoViewBinding
import com.example.kumparanapp.feature.photo.adapter.PhotoViewAdapter
import com.example.kumparanapp.model.photo.Photo

class PhotoViewActivity : BaseActivity<ActivityPhotoViewBinding>() {
    private var AlbumId: String = ""
    private var Id: String = ""
    private var Title: String = ""
    private var Url: String = ""
    private var ThumbnailUrl: String = ""

    var list: ArrayList<Photo> = arrayListOf()

    override fun getViewBinding(): ActivityPhotoViewBinding {
        return ActivityPhotoViewBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        AlbumId = intent.getStringExtra("albumId").orEmpty()
        Id = intent.getStringExtra("id").orEmpty()
        Title = intent.getStringExtra("title").orEmpty()
        Url = intent.getStringExtra("url").orEmpty()
        ThumbnailUrl = intent.getStringExtra("thumbnailUrl").orEmpty()

        list.add(
            Photo(
                AlbumId.toInt(), Id.toInt(), Title, Url, ThumbnailUrl
            )
        )

        list.size



        binding.viewPager.adapter = PhotoViewAdapter(this, list)
    }
}