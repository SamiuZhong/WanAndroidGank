package com.samiu.wangank.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.ui.theme.wanTypography

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
    val articles = viewModel.articles.collectAsLazyPagingItems()
    viewModel.getArticles()

    ListContent(list = articles)
}

@Composable
private fun ListContent(list: LazyPagingItems<ArticleDTO>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = list, key = { article: ArticleDTO ->
            article.id
        }) { article: ArticleDTO? ->
            article?.let { ArticleItem(article = it) }
        }
    }
}

@Composable
private fun ArticleItem(
    article: ArticleDTO
) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Text(text = article.title, style = wanTypography.labelMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItem() {
    ArticleItem(
        article = ArticleDTO(
            title = "Hello World!"
        )
    )
}