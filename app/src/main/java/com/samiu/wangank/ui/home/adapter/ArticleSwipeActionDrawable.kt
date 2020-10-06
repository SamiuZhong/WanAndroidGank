package com.samiu.wangank.ui.home.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.appcompat.content.res.AppCompatResources
import com.samiu.wangank.R
import com.samiu.wangank.util.lerp
import com.samiu.wangank.util.lerpArgb
import com.samiu.wangank.util.themeColor
import com.samiu.wangank.util.themeInterpolator
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.sin

/**
 * @author Samiu 2020/7/6
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class ArticleSwipeActionDrawable(context: Context) : Drawable() {

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = context.themeColor(R.attr.colorSecondary)
        style = Paint.Style.FILL
    }

    private val circle = RectF()
    private var cx = 0F
    private var cr = 0F

    private val icon = AppCompatResources.getDrawable(
        context,
        R.drawable.ic_twotone_star_on_background
    )!!

    private val iconMargin = context.resources.getDimension(R.dimen.grid_4)
    private val iconIntrinsicWidth = icon.intrinsicWidth
    private val iconIntrinsicHeight = icon.intrinsicHeight

    @ColorInt
    private val iconTint = context.themeColor(R.attr.colorOnBackground)

    @ColorInt
    private val iconTintActive = context.themeColor(R.attr.colorOnSecondary)

    private val iconMaxScaleAddition = 0.5F

    private var progress = 0F
        set(value) {
            val constrained = value.coerceIn(0F, 1F)
            if (constrained != field) {
                field = constrained
                callback?.invalidateDrawable(this)
            }
        }

    private var progressAnim: ValueAnimator? = null
    private val dur = context.resources.getInteger(R.integer.reply_motion_duration_medium)
    private val interp = context.themeInterpolator(R.attr.motionInterpolatorPersistent)

    override fun onBoundsChange(bounds: Rect?) {
        if (bounds == null) return
        update()
    }

    private fun update() {
        circle.set(
            bounds.left.toFloat(),
            bounds.top.toFloat(),
            bounds.right.toFloat(),
            bounds.bottom.toFloat()
        )
        val sideToIconCenter = iconMargin + (iconIntrinsicWidth / 2F)
        cx = circle.left + iconMargin + (iconIntrinsicWidth / 2F)
        cr = hypot(circle.right - sideToIconCenter, (circle.height() / 2F))

        callback?.invalidateDrawable(this)
    }

    override fun isStateful(): Boolean = true

    override fun onStateChange(state: IntArray?): Boolean {
        val initialProgress = progress
        val newProgress = if (state?.contains(android.R.attr.state_activated) == true) {
            1F
        } else {
            0F
        }
        progressAnim?.cancel()
        progressAnim = ValueAnimator.ofFloat(initialProgress, newProgress).apply {
            addUpdateListener {
                progress = animatedValue as Float
            }
            interpolator = interp
            duration = (abs(newProgress - initialProgress) * dur).toLong()
        }
        progressAnim?.start()
        return newProgress == initialProgress
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(
            cx,
            circle.centerY(),
            cr * progress,
            circlePaint
        )

        val range = lerp(
            0F,
            Math.PI.toFloat(),
            progress
        )

        val additive = (sin(range.toDouble()) * iconMaxScaleAddition).coerceIn(0.0, 1.0)
        val scaleFactor = 1 + additive
        icon.setBounds(
            (cx - (iconIntrinsicWidth / 2F) * scaleFactor).toInt(),
            (circle.centerY() - (iconIntrinsicHeight / 2F) * scaleFactor).toInt(),
            (cx + (iconIntrinsicWidth / 2F) * scaleFactor).toInt(),
            (circle.centerY() + (iconIntrinsicHeight / 2F) * scaleFactor).toInt()
        )

        icon.setTint(
            lerpArgb(iconTint, iconTintActive, 0F, 0.15F, progress)
        )

        icon.draw(canvas)
    }

    override fun setAlpha(alpha: Int) {
        circlePaint.alpha = alpha
    }

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    override fun setColorFilter(filter: ColorFilter?) {
        circlePaint.colorFilter = filter
    }
}