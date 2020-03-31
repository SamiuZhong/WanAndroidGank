package com.samiu.host.util

import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import com.google.android.material.animation.ArgbEvaluatorCompat
import kotlin.math.roundToInt

/**
 * @author Samiu 2020/3/31
 */
fun lerp(
    startValue: Float,
    endValue: Float,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Float {
    return startValue + fraction * (endValue - startValue)
}

fun lerp(
    startValue: Int,
    endValue: Int,
    @FloatRange(from = 0.0, fromInclusive = true, to = 1.0, toInclusive = true) fraction: Float
): Int {
    return (startValue + fraction * (endValue - startValue)).roundToInt()
}

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