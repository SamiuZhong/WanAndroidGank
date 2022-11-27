package com.samiu.wangank.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.BannerDTO
import com.samiu.wangank.ui.components.ArticleItem

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
    val banners = viewModel.banners.collectAsState()
    val articles = viewModel.articles.collectAsLazyPagingItems()

    viewModel.getBanners()
    viewModel.getArticles()

    ListContent(bannerList = banners.value, articleList = articles)
}

@Composable
private fun ListContent(bannerList: List<BannerDTO>, articleList: LazyPagingItems<ArticleDTO>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            LazyRow {
                items(bannerList) { banner ->
                    AsyncImage(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        model = banner.imagePath,
                        contentDescription = null
                    )
                }
            }
        }
        items(items = articleList, key = { article: ArticleDTO ->
            article.id
        }) { article: ArticleDTO? ->
            article?.let { ArticleItem(article = it) }
        }
    }
}