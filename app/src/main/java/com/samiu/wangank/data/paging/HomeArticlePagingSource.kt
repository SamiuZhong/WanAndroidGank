package com.samiu.wangank.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.model.ArticleDTO

/**
 * 首页文章列表
 *
 * @author samiu 2022/11/27
 * @email samiuzhong@outlook.com
 */
class HomeArticlePagingSource(
    private val apiService: WanApiService,
    private val initialPage: Int
) : PagingSource<Int, ArticleDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDTO> {
        val currentPage = params.key ?: initialPage
        return try {
            val response = apiService.getHomeArticles(currentPage)
            val endOfPaginationReached = response.data.over
            if (!endOfPaginationReached) {
                LoadResult.Page(
                    data = response.data.datas,
                    prevKey = if (currentPage == initialPage) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDTO>): Int? {
        return state.anchorPosition
    }
}