package com.samiu.wangank.ui.home

/**
 * @author Samiu 2022/3/8
 * @email samiuzhong@outlook.com
 */
const val SCROLL_UP = 0x0001
const val SCROLL_DOWN = 0x0002

interface HomeScrollListener {

    fun onHomeScrolled(scrollType: Int)
}