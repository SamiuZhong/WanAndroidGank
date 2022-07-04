package com.samiu.wangank.data.entity

/**
 * @author samiu 2020/5/11
 * @email samiuzhong@outlook.com
 */
data class User(
    val admin: Boolean,
    val chapterTops: List<String>,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
) {
    override fun equals(other: Any?): Boolean {
        return if (other is User) {
            this.id == other.id
        } else false
    }
}