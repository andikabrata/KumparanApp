package com.example.kumparanapp.feature.postdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparanapp.databinding.ItemCommentListBinding
import com.example.kumparanapp.model.postdetail.Comments


class DetailPostAdapter(val onItemClicked: (Comments) -> Unit) :
    RecyclerView.Adapter<DetailPostAdapter.ViewHolder>() {

    var list: ArrayList<Comments> = ArrayList()
    var listFiltered: ArrayList<Comments> = ArrayList()

    fun addData(list: List<Comments>) {
        this.list = list as ArrayList<Comments>
        listFiltered = list
    }

    inner class ViewHolder(val binding: ItemCommentListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailPostAdapter.ViewHolder {
        val binding = ItemCommentListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailPostAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                binding.tvAuhtorName.text = name
                binding.tvBodyComment.text = body
                binding.itemLayout.setOnClickListener {
                    onItemClicked(this)
                }
            }
        }
    }

    override fun getItemCount() = listFiltered.size
}