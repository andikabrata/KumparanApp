package com.example.kumparanapp.feature.userdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparanapp.databinding.ItemAlbumBinding
import com.example.kumparanapp.model.userdetail.Album

class DetailUserAdapter(
    val onItemClicked: (Album) -> Unit
) : RecyclerView.Adapter<DetailUserAdapter.ViewHolder>() {

    var list: ArrayList<Album> = ArrayList()
    var listFiltered: ArrayList<Album> = ArrayList()

    fun addData(list: List<Album>) {
        this.list = list as ArrayList<Album>
        listFiltered = list
    }

    inner class ViewHolder(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlbumBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                binding.tvTitleAlbum.text = title
                binding.itemLayout.setOnClickListener {
                    onItemClicked(this)
                }
            }
        }
    }

    override fun getItemCount() = listFiltered.size
}