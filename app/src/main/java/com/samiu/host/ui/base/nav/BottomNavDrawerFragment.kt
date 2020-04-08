package com.samiu.host.ui.base.nav

import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.samiu.base.ui.BaseFragment
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.FragmentBottomNavDrawerBinding
import kotlin.LazyThreadSafetyMode.NONE
import com.google.android.material.shape.MaterialShapeDrawable
import com.samiu.host.util.lerp
import com.samiu.host.util.themeColor
import com.samiu.host.util.themeInterpolator
import kotlin.math.abs

/**
 * @author Samiu 2020/3/31
 */
class BottomNavDrawerFragment :
    Fragment(),
    NavigationAdapter.NavigationAdapterListener {

    /**
     * 枚举sandwich的状态
     * （这里的账户选择器我们给它取了个名字叫sandwich）
     */
    enum class SandwichState {
        //TODO 登录/注销、我的收藏
        //sandwich处于隐藏，navigation drawer为初始弹出状态
        CLOSED,

        //sandwich处于可见状态
        OPEN,

        //sandwich正处于切换中，这个状态既不是OPEN也不是CLOSED
        SETTLING
    }

    private lateinit var binding: FragmentBottomNavDrawerBinding

    //抽屉栏的behavior
    private val behavior: BottomSheetBehavior<FrameLayout> by lazy(NONE) {
        from(binding.backgroundContainer)
    }

    //抽屉栏的callback
    private val bottomSheetCallback = BottomNavigationDrawerCallback()

    //整个list来存放sandwich滑动的actions
    private val sandwichSlideActions = mutableListOf<OnSandwichSlideAction>()

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
            shapeAppearanceModel = shapeAppearanceModel
                .toBuilder()
                .setTopEdge(    //绘制顶部的凹槽
                    SemiCircleEdgeCutoutTreatment(
                        resources.getDimension(R.dimen.grid_1),
                        resources.getDimension(R.dimen.grid_3),
                        0F,
                        resources.getDimension(R.dimen.navigation_drawer_profile_image_size_padded)
                    )
                )
                .build()
        }
    }

    //sandwich默认CLOSED
    private var sandwichState: SandwichState = SandwichState.CLOSED

    //sandwich的动画
    private var sandwichAnim: ValueAnimator? = null

    //给sandwich的动画整一个插值器
    private val sandwichInterp by lazy(NONE) {
        requireContext().themeInterpolator(R.attr.motionInterpolatorPersistent)
    }

    //sandwich动画的进度，我们需要跟踪这个进度
    private var sandwichProgress: Float = 0F
        set(value) {
            if (field != value) {    //如果set了一个新的值过来
                onSandwichProgressChanged(value)
                //这里根据传过来的value值确定sandwich的状态
                val newState = when (value) {
                    0F -> SandwichState.CLOSED
                    1F -> SandwichState.OPEN
                    else -> SandwichState.SETTLING
                }
                //当状态发生改变时，那么显然我们需要做一些操作
                if (sandwichState != newState)
                    onSandwichStateChanged(newState)
                sandwichState = newState
                field = value
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
    ): View? {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
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
                //关闭sandwich（如果当前它正处于打开状态）
                addOnStateChangedAction(object : OnStateChangedAction {
                    override fun onStateChanged(sheet: View, newState: Int) {
                        sandwichAnim?.cancel()
                        sandwichProgress = 0F
                    }
                })
                //让drawer接收系统返回键
                addOnStateChangedAction(object : OnStateChangedAction {
                    override fun onStateChanged(sheet: View, newState: Int) {
                        closeDrawerOnBackPressed.isEnabled = newState != STATE_HIDDEN
                    }
                })
                //点击头像
                profileImageView.setOnClickListener { toggleSandwich() }

                behavior.addBottomSheetCallback(bottomSheetCallback)
                behavior.state = STATE_HIDDEN

                //给recyclerView设置adapter
                val adapter = NavigationAdapter(this@BottomNavDrawerFragment)
                navRecyclerView.adapter = adapter

                //liveDate订阅数据的变化
                NavigationModel.navigationList.observe(this@BottomNavDrawerFragment) {
                    adapter.submitList(it)
                }
                //默认选中第一项
                NavigationModel.setNavigationMenuItemChecked(0)

                //todo sandwich的item
            }
        }
    }

    fun toggle() {
        when {
            sandwichState == SandwichState.OPEN -> toggleSandwich()
            behavior.state == STATE_HIDDEN -> open()
            behavior.state == STATE_HIDDEN
                    || behavior.state == STATE_HALF_EXPANDED
                    || behavior.state == STATE_EXPANDED
                    || behavior.state == STATE_COLLAPSED -> close()
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

    fun addOnSandwichSlideAction(action: OnSandwichSlideAction) {
        sandwichSlideActions.add(action)
    }

    override fun onNavMenuItemClicked(item: NavigationModelItem.NavMenuItem) {
        if (NavigationModel.setNavigationMenuItemChecked(item.id))
            close()
    }

    /**
     * 打开或者关闭sandwich
     */
    private fun toggleSandwich() {
        //初始化进度
        val initialProgress = sandwichProgress
        val newProgress = when (sandwichState) {
            SandwichState.CLOSED -> {
                //存一下sandwich处于CLOSED状态的原始位置
                //方便我们在sandwich打开和关闭时能找准位置
                binding.backgroundContainer.setTag(
                    R.id.tag_view_top_snapshot,
                    binding.backgroundContainer.top
                )
                1F
            }
            SandwichState.OPEN -> 0F
            SandwichState.SETTLING -> return
        }
        sandwichAnim?.cancel()
        //属性动画
        sandwichAnim = ValueAnimator.ofFloat(initialProgress, newProgress).apply {
            addUpdateListener { sandwichProgress = animatedValue as Float }
            interpolator = sandwichInterp
            duration = (abs(newProgress - initialProgress) *
                    resources.getInteger(R.integer.reply_motion_duration_medium)).toLong()
        }
        //播放动画
        sandwichAnim?.start()
    }

    /**
     * 每当sandwich的动画进度发生变化时调用
     * @param progress sandwich当前的状态，0是CLOSED,1是OPEN
     */
    private fun onSandwichProgressChanged(progress: Float) {
        binding.run {
            val navProgress = lerp(0F, 1F, 0F, 0.5F, progress)
            val accProgress = lerp(0F, 1F, 0.5F, 1F, progress)

            foregroundContainer.translationY =
                (binding.foregroundContainer.height * 0.15F) * navProgress
            profileImageView.scaleX = 1F - navProgress
            profileImageView.scaleY = 1F - navProgress
            profileImageView.alpha = 1F - navProgress
            foregroundContainer.alpha = 1F - navProgress
            accountRecyclerView.alpha = accProgress

            foregroundShapeDrawable.interpolation = 1F - navProgress

            backgroundContainer.translationY =
                progress * ((scrimView.bottom - accountRecyclerView.height -
                        resources.getDimension(R.dimen.bottom_app_bar_height)) -
                        (backgroundContainer.getTag(R.id.tag_view_top_snapshot) as Int))
        }
    }

    /**
     * 每当sandwich的state状态发生变化时调用
     */
    private fun onSandwichStateChanged(state: SandwichState) {
        when (state) {
            SandwichState.OPEN -> binding.run {
                foregroundContainer.visibility = View.GONE
                profileImageView.isClickable = false
            }
            else -> binding.run {
                foregroundContainer.visibility = View.VISIBLE
                profileImageView.isClickable = true
            }
        }
    }
}