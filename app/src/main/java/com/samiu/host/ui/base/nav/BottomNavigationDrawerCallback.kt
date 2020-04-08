package com.samiu.host.ui.base.nav

import android.annotation.SuppressLint
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.samiu.host.util.normalize
import kotlin.math.max

/**
 * @author Samiu 2020/3/31
 */
class BottomNavigationDrawerCallback : BottomSheetBehavior.BottomSheetCallback() {

    //先整个List来存放滑动的actions
    private val onSlideActions: MutableList<OnSlideAction> = mutableListOf()

    //再整个List来存放状态发生改变的actions
    private val onStateChangedActions: MutableList<OnStateChangedAction> = mutableListOf()

    private var lastSlideOffset = -1.0F
    private var halfExpandedSlideOffset = Float.MAX_VALUE

    /**
     * 滑动
     */
    override fun onSlide(sheet: View, slideOffset: Float) {
        if (halfExpandedSlideOffset == Float.MAX_VALUE)
            calculateInitialHalfExpandedSlideOffset(sheet)

        lastSlideOffset = slideOffset

        val trueOffset = if (slideOffset <= halfExpandedSlideOffset)
            slideOffset.normalize(
                -1F,
                halfExpandedSlideOffset,
                -1F,
                0F
            )
        else
            slideOffset.normalize(
                halfExpandedSlideOffset,
                1F,
                0F,
                1F
            )

        onSlideActions.forEach {
            it.onSlide(sheet, trueOffset)
        }
    }

    /**
     * 当状态发生改变
     */
    override fun onStateChanged(sheet: View, newState: Int) {
        if (newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
            halfExpandedSlideOffset = lastSlideOffset
            onSlide(sheet, lastSlideOffset)
        }
        onStateChangedActions.forEach {
            it.onStateChanged(sheet, newState)
        }
    }

    /**
     * 计算抽屉页处于[BottomSheetBehavior.STATE_HALF_EXPANDED]状态的 Offset
     */
    @SuppressLint("PrivateResource")
    private fun calculateInitialHalfExpandedSlideOffset(sheet: View) {
        val parent = sheet.parent as CoordinatorLayout
        val behavior = BottomSheetBehavior.from(sheet)

        val halfExpandedOffset = parent.height * (1 - behavior.halfExpandedRatio)
        val peekHeightMin = parent.resources.getDimensionPixelSize(
            R.dimen.design_bottom_sheet_peek_height_min
        )
        val peek = max(peekHeightMin, parent.height - parent.width * 9 / 16)
        val collapsedOffset = max(
            parent.height - peek,
            max(0, parent.height - sheet.height)
        )
        halfExpandedSlideOffset =
            (collapsedOffset - halfExpandedOffset) / (parent.height - collapsedOffset)
    }

    /**
     * 通过调用这个方法往上面的list里面添加滑动的action
     */
    fun addOnSlideAction(action: OnSlideAction): Boolean {
        return onSlideActions.add(action)
    }

    /**
     * 通过调用这个方法删除上面list里面的滑动action
     */
    fun removeOnSlideAction(action: OnSlideAction): Boolean {
        return onSlideActions.remove(action)
    }

    /**
     * 通过调用这个方法往上面的list里面添加状态改变的action
     */
    fun addOnStateChangedAction(action: OnStateChangedAction): Boolean {
        return onStateChangedActions.add(action)
    }

    /**
     * 通过调用这个方法删除上面list里面的状态改变action
     */
    fun removeOnStateChangedAction(action: OnStateChangedAction): Boolean {
        return onStateChangedActions.remove(action)
    }
}