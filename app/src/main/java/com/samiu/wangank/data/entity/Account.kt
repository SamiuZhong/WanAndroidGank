package com.samiu.wangank.data.entity

import androidx.annotation.DrawableRes

/**
 * @author samiu 2020/4/14
 * @email samiuzhong@outlook.com
 */
data class Account(
    val id:Long,
    @DrawableRes val avatar:Int
)