package com.samiu.host.model.bean

/**
 * @author Samiu 2020/3/3
 */
data class SystemChild(val child: List<SystemChild>,
                       val courseId: Int,
                       val id: Int,
                       val name: String,
                       val order: Int,
                       val parentChapterId: Int,
                       val visible: Int)