package com.samiu.wangank.bean

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
data class SystemParent(
    val children: List<SystemChild>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int,
    var isSelected: Boolean = false,
    val userControlSetTop: Boolean
)