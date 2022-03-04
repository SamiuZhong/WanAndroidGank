package com.samiu.wangank.ui.nav

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.google.android.material.shape.MaterialShapeDrawable
import com.samiu.wangank.R
import com.samiu.wangank.databinding.FragmentBottomNavDrawerBinding
import com.samiu.wangank.global.IS_LOGIN
import com.samiu.wangank.util.desTo
import com.samiu.wangank.bean.Account
import com.samiu.wangank.ui.login.WanLoginActivity
import com.samiu.wangank.ui.login.WanPersonalActivity
import com.samiu.wangank.ui.nav.adapter.NavigationAdapter
import com.samiu.wangank.ui.nav.adapter.NavigationModel
import com.samiu.wangank.ui.nav.adapter.NavigationModelItem
import com.samiu.wangank.util.Preference
import com.samiu.wangank.util.themeColor
import kotlin.LazyThreadSafetyMode.NONE

/**
 * @author Samiu 2020/3/31
 * @email samiuzhong@outlook.com
 */
@SuppressLint("WrongConstant")
class BottomNavDrawerFragment :
    Fragment(),
    NavigationAdapter.NavigationAdapterListener {

    private lateinit var binding: FragmentBottomNavDrawerBinding

    val account = Account(1L, R.drawable.avatar)
    private lateinit var onCutover: (Int) -> Unit

    //抽屉栏的behavior
    private val behavior: BottomSheetBehavior<FrameLayout> by lazy(NONE) {
        from(binding.backgroundContainer)
    }

    //抽屉栏的callback
    private val bottomSheetCallback = BottomNavigationDrawerCallback()

    //抽屉栏下面那层的Drawable
    private val backgroundShapeDrawable: MaterialShapeDrawable by lazy(NONE) {
        val backgroundContext = binding.backgroundContainer.context
        MaterialShapeDrawable(
            backgroundContext,
            null,
            R.attr.bottomSheetStyle,
            0
        ).apply {
            fillColor = ColorStateList.valueOf(
                backgroundContext.themeColor(R.attr.colorPrimarySurfaceVariant)
            )
            elevation = resources.getDimension(R.dimen.plane_08)
            initializeElevationOverlay(requireContext())
        }
    }

    //抽屉栏上面那层的Drawable
    private val foregroundShapeDrawable: MaterialShapeDrawable by lazy(NONE) {
        val foregroundContext = binding.foregroundContainer.context
        MaterialShapeDrawable(
            foregroundContext,
            null,
            R.attr.bottomSheetStyle,
            0
        ).apply {
            fillColor = ColorStateList.valueOf(
                foregroundContext.themeColor(R.attr.colorPrimarySurface)
            )
            elevation = resources.getDimension(R.dimen.plane_16)
            shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_NEVER
            initializeElevationOverlay(requireContext())
            shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                .setTopEdge(    //绘制顶部的凹槽
                    SemiCircleEdgeCutoutTreatment(
                        resources.getDimension(R.dimen.grid_2),
                        resources.getDimension(R.dimen.grid_3),
                        0F,
                        resources.getDimension(R.dimen.navigation_drawer_profile_image_size_padded)
                    )
                )
                .build()
        }
    }

    /**
     * 拦截返回键
     */
    private val closeDrawerOnBackPressed = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            close()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, closeDrawerOnBackPressed)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomNavDrawerBinding.inflate(inflater, container, false)
        binding.foregroundContainer.setOnApplyWindowInsetsListener { v, insets ->
            v.setTag(
                R.id.tag_system_window_inset_top,
                insets.systemWindowInsetTop
            )
            insets
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.account = account
        binding.run {
            //设置上下两层的背景
            backgroundContainer.background = backgroundShapeDrawable
            foregroundContainer.background = foregroundShapeDrawable
            //全屏位置点击返回
            scrimView.setOnClickListener { close() }

            //现在设置抽屉栏的callback回调
            bottomSheetCallback.apply {
                //首先给全屏的这个view添加一个滑动透明度变化的action
                addOnSlideAction(AlphaSlideAction(scrimView))
                //再给这个全屏的view添加一个根据状态改变调整visible的action
                addOnStateChangedAction(VisibilityStateAction(scrimView))
                //抽屉页上层根据滑动来平移
                addOnSlideAction(
                    ForegroundSheetTransformSlideAction(
                        binding.foregroundContainer,
                        foregroundShapeDrawable,
                        binding.profileImageView
                    )
                )
                //根据状态来确定recyclerView的滚动
                addOnStateChangedAction(ScrollToTopStateAction(navRecyclerView))
                //让drawer接收系统返回键
                addOnStateChangedAction(object : OnStateChangedAction {
                    override fun onStateChanged(sheet: View, newState: Int) {
                        closeDrawerOnBackPressed.isEnabled = newState != STATE_HIDDEN
                    }
                })
                //点击头像
                profileImageView.setOnClickListener {
                    val isLogin by Preference(IS_LOGIN, false)
                    toggle()
                    if (isLogin)
                        startActivity(Intent(requireContext(), WanPersonalActivity::class.java))
                    else
                        startActivity(Intent(requireContext(), WanLoginActivity::class.java))
                }

                behavior.addBottomSheetCallback(bottomSheetCallback)
                behavior.state = STATE_HIDDEN

                //给recyclerView设置adapter
                val adapter = NavigationAdapter(this@BottomNavDrawerFragment)
                navRecyclerView.adapter = adapter
                //liveDate订阅数据的变化
                NavigationModel.navigationList.observe(viewLifecycleOwner) {
                    adapter.submitList(it)
                }
                //默认选中第一项
                NavigationModel.setNavigationMenuItemChecked(0)
            }
        }
    }

    /**
     * 底部栏的点击事件
     */
    fun toggle() {
        when (behavior.state) {
            STATE_HIDDEN -> open()
            STATE_HALF_EXPANDED,
            STATE_EXPANDED,
            STATE_COLLAPSED -> close()
        }
    }

    fun open() {
        behavior.state = STATE_HALF_EXPANDED
    }

    fun close() {
        behavior.state = STATE_HIDDEN
    }

    fun addOnSlideAction(action: OnSlideAction) {
        bottomSheetCallback.addOnSlideAction(action)
    }

    fun addOnStateChangedAction(action: OnStateChangedAction) {
        bottomSheetCallback.addOnStateChangedAction(action)
    }

    fun setOnCutover(onCutover:(Int) -> Unit){
        this.onCutover = onCutover
    }

    override fun onNavMenuItemClicked(item: NavigationModelItem.NavMenuItem) {
        if (NavigationModel.setNavigationMenuItemChecked(item.id)) {
            when (item.id) {
                0 -> desTo(this, R.id.wanHomeFragment)
                1 -> desTo(this, R.id.wanSquareFragment)
                2 -> desTo(this, R.id.wanSystemFragment)
                3 -> desTo(this, R.id.wanWxArticleFragment)
                4 -> desTo(this, R.id.wanProjectFragment)
            }
            onCutover(item.titleRes)
            close()
        }
    }
}