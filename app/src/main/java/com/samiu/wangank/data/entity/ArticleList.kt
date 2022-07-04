package com.samiu.wangank.data.entity


/**
 * @author samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
data class ArticleList( val offset: Int,
                        val size: Int,
                        val total: Int,
                        val pageCount: Int,
                        val curPage: Int,
                        val over: Boolean,
                        val datas: List<Article>)