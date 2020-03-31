package com.samiu.host.model.bean

import com.samiu.host.model.bean.Article

/**
 * @author Samiu 2020/3/3
 */
data class ArticleList( val offset: Int,
                        val size: Int,
                        val total: Int,
                        val pageCount: Int,
                        val curPage: Int,
                        val over: Boolean,
                        val datas: List<Article>)