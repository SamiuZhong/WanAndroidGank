package com.samiu.host.model.bean.wan

import com.samiu.host.model.bean.wan.Article

/**
 * @author Samiu 2020/3/3
 */
data class Navigation(val articles: List<Article>,
                      val cid: Int,
                      val name: String)