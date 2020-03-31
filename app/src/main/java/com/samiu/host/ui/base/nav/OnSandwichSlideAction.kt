package com.samiu.host.ui.base.nav

import androidx.annotation.FloatRange

/**
 * @author Samiu 2020/3/31
 */
interface OnSandwichSlideAction {

    fun onSlide(
        @FloatRange(
            from = 0.0,
            fromInclusive = true,
            to = 1.0,
            toInclusive = true
        ) slideOffset: Float
    )
}