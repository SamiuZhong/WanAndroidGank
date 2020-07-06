# WanAndroid_GanK

玩Android大家都很熟悉了，是鸿洋大佬的一个开源知识网站，目前基于玩Android开源API的各种版本APP也多是如牛毛。

那么经过一段时间的潜心修炼，今天给大家带来了一个从未有过的船新版本。

下载安装包体验点击这里

[蓝奏云](https://wws.lanzous.com/iyAMHed8sud)

或者扫描二维码

![](https://upload-images.jianshu.io/upload_images/15143432-c751e70da5b669ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/250)

## 设计

这次的UI整体是根据Google官方的设计，参照Material Design的风格整粗来的，应该能给大家带来耳目一新的感觉。

下面我们先来看看效果图

![](https://upload-images.jianshu.io/upload_images/15143432-edef9291a686c14e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)  ![](https://upload-images.jianshu.io/upload_images/15143432-5fcb1ed75b4e522e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)  ![](https://upload-images.jianshu.io/upload_images/15143432-d95bf89c2295639f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)

![](https://upload-images.jianshu.io/upload_images/15143432-ac44152ee4e40db1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)  ![](https://upload-images.jianshu.io/upload_images/15143432-1d2589fecb6b5dc8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)  ![](https://upload-images.jianshu.io/upload_images/15143432-3baa709048cfe0fd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/260)

底部导航栏的展开动画

<img src="https://upload-images.jianshu.io/upload_images/15143432-9e4e942397f516e8.gif?imageMogr2/auto-orient/strip" width="35%" />

滑动收藏文章

<img src="https://upload-images.jianshu.io/upload_images/15143432-5b6f32108f0043a2.gif?imageMogr2/auto-orient/strip" width="35%" />



## 技术

技术方面采用的是Kotlin语言实现，整体架构为MVVM。

### Model

Model包含本地和网络数据仓库，本地数据使用ROOM交互，网络数据使用Retrofit配合协程调用接口

```kotlin
class WanHomeRepository : BaseWanRepository() {

    suspend fun getBanners(): WanResult<List<Banner>> {
        return readyCall(
            call = {
                call(WanClient.service.getBanner())
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getArticlesList(page: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getHomeArticles(page))
            }, errorMessage = NETWORK_ERROR
        )
    }
}
```

### ViewModel

ViewModel持有Model层，在协程的作用域内与Model进行交互

```kotlin
class WanHomeViewModel(
    private val wanHomeRepository: WanHomeRepository
) : ViewModel() {

    val mArticles = MutableLiveData<List<Article>>()
    val mBanners = MutableLiveData<List<Banner>>()

    fun getBanners() = viewModelScope.launch {
        val data = wanHomeRepository.getBanners()
        if (data is WanResult.Success)
            mBanners.value = data.data
    }

    fun getArticles(page: Int) = viewModelScope.launch {
        val articleList = wanHomeRepository.getArticlesList(page)
        if (articleList is WanResult.Success)
            mArticles.value = articleList.data.datas
    }
}
```

### View

View层即为我们熟悉的Activity和Fragment，通过注入的方式持有ViewModel，调用ViewModel的方法获取数据之后再通过LiveData通知界面进行刷新。

```kotlin
class WanHomeFragment : BaseFragment(R.layout.fragment_wan_home) {

    private val binding by viewBinding(FragmentWanHomeBinding::bind)
    private val viewModel: WanHomeViewModel by viewModel()

	...

    override fun initData() {
        viewModel.getBanners()
        binding.refreshLayout.autoRefresh()
    }

    override fun startObserve() = viewModel.run {
        mBanners.observe(this@WanHomeFragment, Observer { setBanner(it) })
        mArticles.observe(this@WanHomeFragment, Observer { mAdapter.addAll(it) })
    }
}
```

更多实现请clone查看~

欢迎各位看官老爷star鼓励~
