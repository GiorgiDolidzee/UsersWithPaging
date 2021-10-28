package com.example.homework14.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.homework14.UsersPagingSource

class UsersViewModel: ViewModel() {
    fun loadUsers() =
        Pager(config = PagingConfig(1), pagingSourceFactory = {UsersPagingSource()})
            .liveData.cachedIn(viewModelScope)
}
