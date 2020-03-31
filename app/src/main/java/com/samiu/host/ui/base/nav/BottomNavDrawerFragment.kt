package com.samiu.host.ui.base.nav

import android.accounts.Account
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.view.View
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentBottomNavDrawerBinding
import kotlin.LazyThreadSafetyMode.NONE
import com.google.android.material.bottomsheet.BottomSheetBehavior.from
import com.google.android.material.shape.MaterialShapeDrawable
import com.samiu.host.util.lerp
import com.samiu.host.util.themeColor
import com.samiu.host.util.themeInterpolator

/**
 * @author Samiu 2020/3/31
 */
class BottomNavDrawerFragment :
    BaseFragment(R.layout.fragment_bottom_nav_drawer),
    NavigationAdapter.NavigationAdapterListener,
    AccountAdapter.AccountAdapterListener {

    private val binding by viewBinding(FragmentBottomNavDrawerBinding::bind)
    override fun initView() = Unit
    override fun initData() = Unit

    /**
     * 枚举账户选择器的状态
     */
    enum class SandwichState {
        //选择器处于隐藏状态，navigation drawer初始状态
        CLOSED,

        //选择器处于可见状态
        OPEN,

        //切换动画正在播放，这个状态既不是OPEN也不是CLOSED
        SETTLING
    }

    //抽屉栏的behavior
    private val behavior: BottomSheetBehavior<FrameLayout> by lazy(NONE) {
        from(binding.backgroundContainer)
    }
    //抽屉栏的callback
    private val bottomSheetCallback = BottomNavigationDrawerCallback()
    private val sandwichSlideAction = mutableListOf<OnSandwichSlideAction>()

    override fun onAccountClicked(account: Account) {
        TODO("Not yet implemented")
    }

}