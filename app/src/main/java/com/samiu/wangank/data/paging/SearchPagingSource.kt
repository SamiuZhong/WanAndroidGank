package com.samiu.wangank.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.samiu.wangank.data.remote.WanApiService
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.utils.Constants
import okio.IOException
import retrofit2.HttpException

/**
 * 文章列表
 *
 * @author samiu 2023/3/1
 * @email samiuzhong@outlook.com
 */
class SearchPagingSource(
    private val service: WanApiService,
    private val keyword: String
) : PagingSource<Int, ArticleDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDTO> {
        return try {
            val startIndex = Constants.Network.STARTING_PAGE_INDEX_0
            val currentPage = params.key ?: startIndex
            val response = service.search(currentPage, keyword, Constants.Network.DEFAULT_PAGE_SIZE)
            val articles = response.data.datas
            LoadResult.Page(
                data = articles,
                prevKey = if (currentPage > 0) currentPage.minus(1) else null,
                nextKey = if (articles.isNotEmpty()) currentPage.plus(1) else null
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDTO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}