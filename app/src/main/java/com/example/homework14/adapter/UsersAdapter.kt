package com.example.homework14.adapter

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework14.R
import com.example.homework14.databinding.ItemUserBinding
import com.example.homework14.extensions.setImage
import com.example.homework14.model.User

class UsersAdapter() : PagingDataAdapter<User.Data, UsersAdapter.ViewHolder>(DiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: User.Data) {
            binding.ivAvatar.setImage(model.avatar)
            binding.tvFirstname.text = model.firstName.plus(model.lastName)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<User.Data>() {
        override fun areItemsTheSame(oldItem: User.Data, newItem: User.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User.Data, newItem: User.Data): Boolean {
            return oldItem == newItem
        }

    }

}
