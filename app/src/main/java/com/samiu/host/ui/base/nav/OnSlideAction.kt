package com.samiu.host.ui.base.nav

import android.view.View
import android.widget.ImageView
import androidx.annotation.FloatRange
import androidx.core.view.marginTop
import androidx.core.view.updatePadding
import com.google.android.material.shape.MaterialShapeDrawable
import com.samiu.host.R
import com.samiu.host.util.normalize

/**
 * @author Samiu 2020/3/31
 */
interface OnSlideAction {

    fun onSlide(
        sheet: View,
        @FloatRange(
            from = -1.0,
            fromInclusive = true,
            to = 1.0,
            toInclusive = true
        ) slideOffset: Float
    )
}

/**
 * 将给过来的view逆时针旋转180度
 */
class HalfClockwiseRotateSlideAction(
    private val view: View
) : OnSlideAction {
    override fun onSlide(sheet: View, slideOffset: Float) {
        view.rotation = slideOffset.normalize(
            -1F,
            0F,
            0F,
            180F
        )
    }
}

/**
 * 导航页的抽屉栏从初始的半展开状态到完全展开
 * 包括：
 * @param profileImageView 用户头像的缩放
 * @param foregroundView 上层View的平移
 * @param foregroundShapeDrawable 圆角和缺口的处理
 */
class ForegroundSheetTransformSlideAction(
    private val foregroundView: View,
    private val foregroundShapeDrawable: MaterialShapeDrawable,
    private val profileImageView: ImageView
) : OnSlideAction {

    private val foregroundMarginTop = foregroundView.marginTop
    private var systemTopInset: Int = 0
    private val foregroundZ = foregroundView.z
    private val profileImageOriginalZ = profileImageView.z

    private fun getPaddingTop(): Int {
        if (systemTopInset == 0)
            systemTopInset = foregroundView.getTag(R.id.tag_system_window_inset_top) as? Int? ?: 0
        return systemTopInset
    }

    override fun onSlide(sheet: View, slideOffset: Float) {
        val progress = slideOffset.normalize(
            0F,
            0.25F,
            1F,
            0F
        )
        profileImageView.scaleX = progress
        profileImageView.scaleY = progress
        foregroundShapeDrawable.interpolation = progress

        foregroundView.translationY = -(1 - progress) * foregroundMarginTop
        val topPaddingProgress = slideOffset.normalize(0F, 0.9F, 0F, 1F)
        foregroundView.updatePadding(
            top = (getPaddingTop() * topPaddingProgress).toInt()
        )

        if (slideOffset > 0 && foregroundZ <= profileImageView.z)
            profileImageView.z = profileImageOriginalZ
        else if (slideOffset <= 0 && foregroundZ >= profileImageView.z)
            profileImageView.z = foregroundZ + 1
    }
}

/**
 * View透明度变化
 */
class AlphaSlideAction(
    private val view: View,
    private val reverse: Boolean = false
) : OnSlideAction {

    override fun onSlide(sheet: View, slideOffset: Float) {
        val alpha = slideOffset.normalize(-1F, 0F, 0F, 1F)
        view.alpha =
            if (!reverse)
                alpha
            else
                1F - alpha
    }
}