package com.samiu.wangank.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.ui.components.ArticleItem
import com.samiu.wangank.ui.components.WanSearchBar

/**
 * 首页
 *
 * @author samiu 2022/11/26
 * @email samiuzhong@outlook.com
 */
@Composable
fun HomeScreen(
    navController: NavController, viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState = remember { viewModel.uiState }
    val articles = uiState.articleFlow.collectAsLazyPagingItems()
    val listState = if (articles.itemCount > 0) uiState.listState else rememberLazyListState()

    ListContent(lazyListState = listState, pagingItems = articles)
}

@Composable
private fun ListContent(lazyListState: LazyListState, pagingItems: LazyPagingItems<ArticleDTO>) {
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            WanSearchBar(modifier = Modifier.fillMaxWidth())
        }
        items(items = pagingItems, key = { article: ArticleDTO ->
            article.id
        }) { article: ArticleDTO? ->
            article?.let { ArticleItem(article = it) }
        }
    }
}