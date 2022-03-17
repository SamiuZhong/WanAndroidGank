package com.samiu.wangank.ui.mine.personal

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.samiu.wangank.bean.Article
import com.samiu.wangank.http.WanClient

/**
 * @author Samiu 2022/3/8
 * @email samiuzhong@outlook.com
 */
class MyArticlePagingSource : PagingSource<Long, Article>() {

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Article> {
        return try {
            val currentPage = getCurrentPage(params.key)
            val response = WanClient.service.getMyArticles(currentPage)
            Log.d("Paging3", "Paging加载Article数据：第${currentPage}页面")

            LoadResult.Page(
                data = response.data!!.shareArticles.datas,
                prevKey = if (currentPage > 1) currentPage - 1L else null,
                nextKey = if (response.data.shareArticles.datas.isNotEmpty()) currentPage + 1L else null
            )
        } catch (exception: Exception) {
            Log.d("Paging3", "Paging加载Article数据,Error = $exception")
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Long, Article>): Long? {
        return state.anchorPosition?.toLong()
    }

    private fun getCurrentPage(page: Long?): Long {
        var result = 1L
        page?.let {
            if (page > 1)
                result = page
        }
        return result
    }
}