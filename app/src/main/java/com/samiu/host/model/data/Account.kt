package com.samiu.host.model.data

import androidx.annotation.DrawableRes
import com.samiu.host.R

/**
 * @author Samiu 2020/4/14
 */
data class Account(
    val id:Long,
    @DrawableRes val avatar:Int
)