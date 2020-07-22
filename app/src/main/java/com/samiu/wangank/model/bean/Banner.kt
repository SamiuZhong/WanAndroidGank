package com.samiu.wangank.model.bean

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
data class Banner(val desc: String,
                  val id: Int,
                  val imagePath: String,
                  val isVisible: Int,
                  val order: Int,
                  val title: String,
                  val type: Int,
                  val url: String)