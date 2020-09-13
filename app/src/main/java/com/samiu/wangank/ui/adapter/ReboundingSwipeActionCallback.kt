package com.samiu.wangank.ui.adapter

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.ln

/**
 * @author Samiu 2020/7/6
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */

//右滑接近阈值的时候降低dX的滑动速度
private const val swipeReboundingElasticity = 0.8F

//横向滑动到超过这个值（即40%）时视为有效的一次滑动
private const val trueSwipeThresholds = 0.4F

class ReboundingSwipeActionCallback : ItemTouchHelper.SimpleCallback(
    0, ItemTouchHelper.RIGHT
) {

    interface ReboundableViewHolder {
        val reboundableView: View

        /**
         * 滑动/回弹事件正在进行
         *
         * @param currentSwipePercentage 整体的滑动百分比
         * @param swipeThreshold 当前到底是处于滑动还是回弹
         * @param currentTargetHasMetThresholdOnce 在整个连续的滑动/回弹过程之中，当前百分比是否大于阈值
         */
        fun onReboundOffsetChanged(
            currentSwipePercentage: Float,
            swipeThreshold: Float,
            currentTargetHasMetThresholdOnce: Boolean
        )

        /**
         * 滑动交互结束并且超过了阈值，才会调用此方法
         */
        fun onRebounded()
    }

    private var currentTargetPosition: Int = -1
    private var currentTargetHasMetThresholdOnce: Boolean = false

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float = Float.MAX_VALUE
    override fun getSwipeVelocityThreshold(defaultValue: Float): Float = Float.MAX_VALUE
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float = Float.MAX_VALUE

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if (currentTargetHasMetThresholdOnce && viewHolder is ReboundableViewHolder) {
            currentTargetHasMetThresholdOnce = false
            viewHolder.onRebounded()
        }
        super.clearView(recyclerView, viewHolder)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (viewHolder !is ReboundableViewHolder) return
        if (currentTargetPosition != viewHolder.adapterPosition) {
            currentTargetPosition = viewHolder.adapterPosition
            currentTargetHasMetThresholdOnce = false
        }

        val itemView = viewHolder.itemView
        val currentSwipePercentage = abs(dX) / itemView.width
        viewHolder.onReboundOffsetChanged(
            currentSwipePercentage,
            trueSwipeThresholds,
            currentTargetHasMetThresholdOnce
        )
        translateReboundingView(itemView, viewHolder, dX)

        if (currentSwipePercentage >= trueSwipeThresholds &&
            !currentTargetHasMetThresholdOnce
        ) {
            currentTargetHasMetThresholdOnce = true
        }
    }

    private fun translateReboundingView(
        itemView: View,
        viewHolder: ReboundableViewHolder,
        dX: Float
    ) {
        val swipeDismissDistanceHorizontal = itemView.width * trueSwipeThresholds
        val dragFraction = ln(
            (1 + (dX / swipeDismissDistanceHorizontal)).toDouble()
        ) / ln(
            3.toDouble()
        )
        val dragTo = dragFraction * swipeDismissDistanceHorizontal *
                swipeReboundingElasticity

        viewHolder.reboundableView.translationX = dragTo.toFloat()
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = Unit
}