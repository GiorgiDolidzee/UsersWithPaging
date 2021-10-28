package com.example.homework14.fragments

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework14.adapter.UsersAdapter
import com.example.homework14.adapter.UsersLoadStateAdapter
import com.example.homework14.databinding.FragmentUsersBinding
import com.example.homework14.viewmodel.UsersViewModel

class UsersFragment : BaseFragment<FragmentUsersBinding>(FragmentUsersBinding::inflate) {

    private val viewModel: UsersViewModel by viewModels()
    private lateinit var adapter: UsersAdapter

    override fun start() {
        initUsersRecycler()
    }

    private fun initUsersRecycler() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UsersAdapter()
        binding.recyclerView.adapter = adapter

        binding.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = UsersLoadStateAdapter{ adapter.retry() },
            footer = UsersLoadStateAdapter{ adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
        }

        lifecycleScope.launchWhenCreated {
            viewModel.loadUsers().observe(viewLifecycleOwner, {
                adapter.submitData(lifecycle, it)
            })
        }
    }

}