package com.example.kumparanapp.feature.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparanapp.core.db.entity.UserMirrorEntity
import com.example.kumparanapp.databinding.ItemListBinding
import com.example.kumparanapp.model.post.Posts

class PostAdapter(val onItemClicked: (Posts) -> Unit) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var list: ArrayList<Posts> = ArrayList()
    var listFiltered: ArrayList<Posts> = ArrayList()
    var listUser: ArrayList<UserMirrorEntity> = ArrayList()
    var listUser2: ArrayList<UserMirrorEntity> = ArrayList()

    fun addData(list: List<Posts>, listUser: List<UserMirrorEntity>) {
        this.list = list as ArrayList<Posts>
        this.listUser = listUser as ArrayList<UserMirrorEntity>
        listFiltered = list
        listUser2 = listUser
    }

    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                binding.tvUsername.text = listUser2[position].Username
                binding.tvCompanyName.text = listUser2[position].CompanyName
                binding.tvTitle.text = title
                binding.tvBody.text = body
                binding.itemLayout.setOnClickListener {
                    onItemClicked(this)
                }
            }
        }
    }

    override fun getItemCount() = listFiltered.size
}