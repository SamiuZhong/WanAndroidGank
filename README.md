# WanAndroid_Gank

玩Android大家都很熟悉了，是鸿洋大佬的一个开源知识网站，目前基于玩Android开源API的各种版本APP也多是如牛毛。

那么经过一段时间的潜心修炼，今天给大家带来了一个从未有过的船新版本。

## 设计

这次的UI整体是根据Google官方的设计，参照Material Design的风格整粗来的，应该能给大家带来耳目一新的感觉。

下面我们先来看看效果图

![](https://upload-images.jianshu.io/upload_images/15143432-c88fb00f261b68b2.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)  ![](https://upload-images.jianshu.io/upload_images/15143432-eb95e2a2fe839d3a.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)  ![](https://upload-images.jianshu.io/upload_images/15143432-be2adaa59b95c972.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)

![](https://upload-images.jianshu.io/upload_images/15143432-985ec5bfcc9d01fd.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)  ![](https://upload-images.jianshu.io/upload_images/15143432-b72e69cc373317c6.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)  ![](https://upload-images.jianshu.io/upload_images/15143432-838bbb7a15030489.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)

底部导航栏使用了BottomAppbar和FloatingActionButton组件

MaterialShapeDrawable实现了展开菜单的Material丝滑动画

<img src="https://upload-images.jianshu.io/upload_images/15143432-43b256804130d85c.gif?imageMogr2/auto-orient/strip" width="50%" />

```kotlin
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
                .setTopEdge(    
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
```



## 技术

技术方面采用的是Kotlin语言实现，整体架构为MVVM，使用协程配合Retrofit来创建和处理网络请求的异步任务。

封装了dataBinding和viewBinding的懒加载

```kotlin
/**
 * Fragment ViewBinding Delegate
 */
fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {
    private var _binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            _binding = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = _binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return viewBindingFactory(thisRef.requireView()).also { _binding = it }
    }
}
```

欢迎各位看官老爷star鼓励~
