package com.samiu.host.model.bean.wan

/**
 * @author Samiu 2020/3/3
 */
data class SystemParent(val children: List<SystemChild>,
                        val courseId: Int,
                        val id: Int,
                        val name: String,
                        val order: Int,
                        val parentChapterId: Int,
                        val visible: Int,
                        val userControlSetTop: Boolean)