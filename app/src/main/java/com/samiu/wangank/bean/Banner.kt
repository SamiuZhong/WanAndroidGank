package com.samiu.wangank.bean

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
data class Banner(val desc: String,
                  val id: Int,
                  val imagePath: String,
                  val isVisible: Int,
                  val order: Int,
                  val title: String,
                  val type: Int,
                  val url: String)