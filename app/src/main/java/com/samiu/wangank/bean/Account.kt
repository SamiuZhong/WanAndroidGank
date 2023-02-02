package com.samiu.wangank.bean

import androidx.annotation.DrawableRes

/**
 * @author Samiu 2020/4/14
 * @email samiuzhong@outlook.com
 */
data class Account(
    val id:Long,
    @DrawableRes val avatar:Int
)