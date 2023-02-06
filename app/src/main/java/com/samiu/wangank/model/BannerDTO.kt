package com.samiu.wangank.model

/**
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
data class BannerDTO(
    val desc: String = "",
    val id: Int = 0,
    val imagePath: String = "",
    val isVisible: Int = 0,
    val order: Int = 0,
    val title: String = "",
    val type: Int = 0,
    val url: String = ""
)