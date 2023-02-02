package com.samiu.wangank.util

import android.content.Context
import android.content.res.ColorStateList
import com.google.android.material.shape.MaterialShapeDrawable
import com.samiu.wangank.R

/**
 * 绘制ShapeDrawable
 *
 * @author Samiu 2020/9/17
 * @email samiuzhong@outlook.com
 */
fun drawShape(
    context: Context, cornerSize: Int?, color: Int
) = MaterialShapeDrawable(
    context,
    null,
    0,
    0
).apply {
    val realColor = context.resources.getColor(color,null)
    fillColor = ColorStateList.valueOf(realColor)
    shapeAppearanceModel = shapeAppearanceModel
        .toBuilder()
        .setAllCornerSizes(context.resources.getDimension(cornerSize ?: R.dimen.corner_0))
        .build()
}

fun drawShape(
    context: Context,
    ltCorner: Int?,
    rtCorner: Int?,
    lbCorner: Int?,
    rbCorner: Int?,
    color: Int
) = MaterialShapeDrawable(
    context,
    null,
    0,
    0
).apply {
    val realColor = context.resources.getColor(color,null)
    fillColor = ColorStateList.valueOf(realColor)
    shapeAppearanceModel = shapeAppearanceModel
        .toBuilder()
        .setTopLeftCornerSize(context.resources.getDimension(ltCorner ?: R.dimen.corner_0))
        .setTopRightCornerSize(context.resources.getDimension(rtCorner ?: R.dimen.corner_0))
        .setBottomLeftCornerSize(context.resources.getDimension(lbCorner ?: R.dimen.corner_0))
        .setBottomRightCornerSize(context.resources.getDimension(rbCorner ?: R.dimen.corner_0))
        .build()
}

fun drawShape(
    context: Context, cornerSize: Int?, color: Int, lineColor: Int
) = MaterialShapeDrawable(
    context,
    null,
    0,
    0
).apply {
    val realColor = context.resources.getColor(color,null)
    val realLineColor = context.resources.getColor(lineColor,null)
    fillColor = ColorStateList.valueOf(realColor)
    strokeWidth = 1F
    strokeColor = ColorStateList.valueOf(realLineColor)
    shapeAppearanceModel = shapeAppearanceModel
        .toBuilder()
        .setAllCornerSizes(context.resources.getDimension(cornerSize ?: R.dimen.corner_0))
        .build()
}