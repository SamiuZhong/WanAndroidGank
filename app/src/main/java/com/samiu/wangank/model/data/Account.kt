package com.samiu.wangank.model.data

import androidx.annotation.DrawableRes

/**
 * @author Samiu 2020/4/14
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
data class Account(
    val id:Long,
    @DrawableRes val avatar:Int
)