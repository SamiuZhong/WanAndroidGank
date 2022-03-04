package com.samiu.wangank.ui.nav

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath
import kotlin.math.atan
import kotlin.math.sqrt

private const val ARC_QUARTER = 90
private const val ARC_HALF = 180
private const val ANGLE_UP = 270
private const val ANGLE_LEFT = 180

/**
 * @author Samiu 2020/3/31
 * @email samiuzhong@outlook.com
 */

/**
 * 在边缘绘制半圆形的凹槽
 */
class SemiCircleEdgeCutoutTreatment(
    private var cutoutMargin: Float = 0F,   //上面的头像离这个凹槽的间隔距离
    private var cutoutRoundedCornerRadius: Float = 0F,  //凹槽两边那个反向的圆角
    private var cutoutVerticalOffset: Float = 0F,   //凹槽的竖直偏移量，为0刚好是个半圆
    private var cutoutDiameter: Float = 0F, //凹槽的直径
    private var cutoutHorizontalOffset: Float = 0F  //凹槽的水平 偏移量，为0刚好是个半圆
) : EdgeTreatment() {

    private var cradleDiameter = 0F
    private var cradleRadius = 0F
    private var roundedCornerOffset = 0F
    private var middle = 0F
    private var verticalOffset = 0F
    private var verticalOffsetRatio = 0F
    private var distanceBetweenCenters = 0F
    private var distanceBetweenCentersSquared = 0F
    private var distanceY = 0F
    private var distanceX = 0F
    private var leftRoundedCornerCircleX = 0F
    private var rightRoundedCornerCircleX = 0F
    private var cornerRadiusArcLength = 0F
    private var cutoutArcOffset = 0F

    init {
        require(cutoutVerticalOffset >= 0) {
            "cutoutVerticalOffset must be positive but was $cutoutVerticalOffset"
        }
    }

    override fun getEdgePath(
        length: Float,
        center: Float,
        interpolation: Float,
        shapePath: ShapePath
    ) {
        //直径为0，那就没啥好画的了，整条直线吧
        if (cutoutDiameter == 0f) {
            shapePath.lineTo(length, 0f)
            return
        }

        cradleDiameter = cutoutMargin * 2 + cutoutDiameter
        cradleRadius = cradleDiameter / 2f
        roundedCornerOffset = interpolation * cutoutRoundedCornerRadius
        middle = length / 2f + cutoutHorizontalOffset

        verticalOffset = interpolation * cutoutVerticalOffset +
                (1 - interpolation) * cradleRadius
        verticalOffsetRatio = verticalOffset / cradleRadius

        if (verticalOffsetRatio >= 1.0f) {
            shapePath.lineTo(length, 0f)
            return
        }

        distanceBetweenCenters = cradleRadius + roundedCornerOffset
        distanceBetweenCentersSquared = distanceBetweenCenters * distanceBetweenCenters
        distanceY = verticalOffset + roundedCornerOffset
        distanceX = sqrt(
            (distanceBetweenCentersSquared - distanceY * distanceY).toDouble()
        ).toFloat()

        leftRoundedCornerCircleX = middle - distanceX
        rightRoundedCornerCircleX = middle + distanceX

        cornerRadiusArcLength = Math.toDegrees(
            atan((distanceX / distanceY).toDouble())
        ).toFloat()
        cutoutArcOffset = ARC_QUARTER - cornerRadiusArcLength

        shapePath.lineTo(leftRoundedCornerCircleX - roundedCornerOffset, 0f)

        shapePath.addArc(
            leftRoundedCornerCircleX - roundedCornerOffset,
            0f,
            leftRoundedCornerCircleX + roundedCornerOffset,
            roundedCornerOffset * 2,
            ANGLE_UP.toFloat(),
            cornerRadiusArcLength
        )

        shapePath.addArc(
            middle - cradleRadius,
            -cradleRadius - verticalOffset,
            middle + cradleRadius,
            cradleRadius - verticalOffset,
            ANGLE_LEFT - cutoutArcOffset,
            cutoutArcOffset * 2 - ARC_HALF
        )

        shapePath.addArc(
            rightRoundedCornerCircleX - roundedCornerOffset,
            0f,
            rightRoundedCornerCircleX + roundedCornerOffset,
            roundedCornerOffset * 2,
            ANGLE_UP - cornerRadiusArcLength,
            cornerRadiusArcLength
        )

        shapePath.lineTo(length, 0f)
    }
}
