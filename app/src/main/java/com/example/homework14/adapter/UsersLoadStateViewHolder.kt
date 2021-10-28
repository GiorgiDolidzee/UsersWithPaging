package com.example.homework14.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.homework14.R
import com.example.homework14.databinding.LoadstateitemBinding

class UsersLoadStateViewHolder(
    private val binding: LoadstateitemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): UsersLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.loadstateitem, parent, false)
            val binding = LoadstateitemBinding.bind(view)
            return UsersLoadStateViewHolder(binding, retry)
        }
    }
}