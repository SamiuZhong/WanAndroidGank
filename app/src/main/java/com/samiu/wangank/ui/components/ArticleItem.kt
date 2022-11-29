package com.samiu.wangank.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.ui.theme.wanTypography

/**
 * @author samiu 2022/11/27
 * @email samiuzhong@outlook.com
 */
@Composable
fun ArticleItem(
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
            sortId = 0,
            title = "Hello World!"
        )
    )
}