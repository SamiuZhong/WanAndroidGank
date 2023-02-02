package com.samiu.wangank.util

import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import com.google.android.material.animation.ArgbEvaluatorCompat
import kotlin.math.roundToInt

/**
 * @author Samiu 2020/3/31
 * @email samiuzhong@outlook.com
 */

/**
 * 在start和end两个值之间线性插值
 * @param startValue
 * @param endValue
 */
fun lerp(
    startValue: Float,
    endValue: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Float {
    return startValue + fraction * (endValue - startValue)
}

/**
 * 在start和end两个值之间线性插值
 * @param startValue
 * @param endValue
 */
fun lerp(
    startValue: Int,
    endValue: Int,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Int {
    return (startValue + fraction * (endValue - startValue)).roundToInt()
}

/**
 * 当传入的值在给定的范围之内，我们才进行线性的插值
 */
fun lerp(
    startValue: Float,
    endValue: Float,
    @FloatRange(
        from = 0.0,
        fromInclusive = true,
        to = 1.0,
        toInclusive = false
    ) startFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = false, to = 1.0, toInclusive = true) endFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Float {
    if (fraction < startFraction) return startValue
    if (fraction > endFraction) return endValue

    return lerp(startValue, endValue, (fraction - startFraction) / (endFraction - startFraction))
}

/**
 * 当传入的值在给定的范围之内，我们才进行线性的插值
 */
fun lerp(
    startValue: Int,
    endValue: Int,
    @FloatRange(
        from = 0.0,
        fromInclusive = true,
        to = 1.0,
        toInclusive = false
    ) startFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = false, to = 1.0, toInclusive = true) endFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Int {
    if (fraction < startFraction) return startValue
    if (fraction > endFraction) return endValue

    return lerp(startValue, endValue, (fraction - startFraction) / (endFraction - startFraction))
}

/**
 * 当传入的值在给定的范围之内，我们在两种颜色之间进行线性的插值
 * @param startColor
 * @param endColor
 */
@ColorInt
fun lerpArgb(
    @ColorInt startColor: Int,
    @ColorInt endColor: Int,
    @FloatRange(
        from = 0.0,
        fromInclusive = true,
        to = 1.0,
        toInclusive = false
    ) startFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = false, to = 1.0, toInclusive = true) endFraction: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Int {
    if (fraction < startFraction) return startColor
    if (fraction > endFraction) return endColor

    return ArgbEvaluatorCompat.getInstance().evaluate(
        (fraction - startFraction) / (endFraction - startFraction),
        startColor,
        endColor
    )
}

/**
 * 我们为Float写的一个扩展方法
 * 作用是把这个Float值变成我们想要的Float值
 */
fun Float.normalize(
    inputMin: Float,
    inputMax: Float,
    outputMin: Float,
    outputMax: Float
): Float {
    if (this < inputMin) {
        return outputMin
    } else if (this > inputMax) {
        return outputMax
    }

    return outputMin * (1 - (this - inputMin) / (inputMax - inputMin)) +
            outputMax * ((this - inputMin) / (inputMax - inputMin))
}