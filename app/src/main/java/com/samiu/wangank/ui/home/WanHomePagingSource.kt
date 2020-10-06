package com.samiu.wangank.ui.home

import android.util.Log
import androidx.paging.PagingSource
import com.samiu.wangank.bean.ArticleItem
import com.samiu.wangank.http.WanApiService

/**
 * @author Samiu 2020/10/6
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanHomePagingSource(
    private val apiService: WanApiService
) : PagingSource<Int, ArticleItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleItem> {
        return try {
            val currentPage = params.key ?: 0
            val response = apiService.getHomeArticles(currentPage)
            Log.d("Paging3", "Paging加载数据：第${currentPage}页面")
            LoadResult.Page(
                data = response.data.datas,
                prevKey = if (currentPage > 0) currentPage.minus(1) else null,
                nextKey = if (response.data.over) null else currentPage.plus(1)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}