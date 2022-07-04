package com.samiu.wangank.data.entity

/**
 * @author samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
data class SystemChild(val child: List<SystemChild>,
                       val courseId: Int,
                       val id: Int,
                       val name: String,
                       val order: Int,
                       val parentChapterId: Int,
                       val visible: Int)