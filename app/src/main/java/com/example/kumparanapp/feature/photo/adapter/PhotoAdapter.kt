package com.example.kumparanapp.feature.photo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparanapp.common.extension.loadImage
import com.example.kumparanapp.common.extension.loadImageCard
import com.example.kumparanapp.databinding.ItemPhotoBinding
import com.example.kumparanapp.model.photo.Photo


class PhotoAdapter(val onItemClicked: (Photo) -> Unit) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var list: ArrayList<Photo> = ArrayList()
    var listFiltered: ArrayList<Photo> = ArrayList()

    fun addData(list: List<Photo>) {
        this.list = list as ArrayList<Photo>
        listFiltered = list
    }

    inner class ViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPhotoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                /*val url = GlideUrl(
                    "https://via.placeholder.com", LazyHeaders.Builder()
                        .addHeader("User-Agent", "your-user-agent")
                        .build()
                )*/

                binding.photo.loadImage("$thumbnailUrl.jpg")
                binding.itemLayout.setOnClickListener {
                    onItemClicked(this)
                }
            }
        }
    }

    override fun getItemCount() = listFiltered.size

}