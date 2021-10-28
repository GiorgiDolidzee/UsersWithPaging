package com.example.homework14

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.homework14.model.User
import com.example.homework14.network.NetworkClient
import kotlinx.coroutines.delay

class UsersPagingSource : PagingSource<Int, User.Data>() {
    override fun getRefreshKey(state: PagingState<Int, User.Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User.Data> {
        val page: Int = params.key ?: 1
        return try {
            val response = NetworkClient.apiClient.getUsers(page)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                var prevPage: Int? = null
                var nextPage: Int? = null
                if (body.totalPages!! > page) {
                    nextPage = page + 1
                }
                if (page != 1) {
                    prevPage = page - 1
                }

                LoadResult.Page(
                    body.data!!,
                    prevPage,
                    nextPage
                )
            } else {
                LoadResult.Error(Throwable())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}