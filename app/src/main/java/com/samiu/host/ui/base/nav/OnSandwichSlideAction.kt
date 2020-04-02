package com.samiu.host.ui.base.nav

import android.view.View
import androidx.annotation.FloatRange
import com.samiu.host.util.normalize

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

/**
 * 将给过来的view逆时针旋转180度
 */
class HalfCounterClockwiseRotateSlideAction(
    private val view: View
) : OnSandwichSlideAction {
    override fun onSlide(slideOffset: Float) {
        view.rotation = slideOffset.normalize(
            0F,
            1F,
            180F,
            0F
        )
    }
}