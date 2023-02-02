package com.samiu.wangank.ui.wxpub.adapter

import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ItemWanArticleBinding
import com.samiu.wangank.bean.Article
import com.samiu.wangank.databinding.ItemWxArticleBinding
import com.samiu.wangank.ui.home.adapter.ArticleSwipeActionDrawable
import com.samiu.wangank.ui.home.adapter.ReboundingSwipeActionCallback
import kotlin.math.abs

/**
 * @author Samiu 2020/7/6
 * @email samiuzhong@outlook.com
 */
class WxArticleViewHolder(
    private val binding: ItemWxArticleBinding,
    listener: WxArticleAdapter.ArticleAdapterListener
) : RecyclerView.ViewHolder(binding.root), ReboundingSwipeActionCallback.ReboundableViewHolder {

    private val starredCornerSize =
        itemView.resources.getDimension(R.dimen.corner_24)

    override val reboundableView: View
        get() = binding.cardView

    init {
        binding.run {
            this.listener = listener
            root.background = ArticleSwipeActionDrawable(root.context)
        }
    }

    fun bind(article: Article) {
        binding.article = article
        binding.root.isActivated = article.collect
        binding.itemTitle.text = Html.fromHtml(article.title,0)
        if (article.author != null && article.author.isNotEmpty()) {
            binding.itemAuthor.text = article.author
        } else if (article.shareUser != null && article.shareUser.isNotEmpty()) {
            binding.itemAuthor.text = article.shareUser
        } else {
            binding.itemAuthor.text = binding.itemAuthor.context.getString(R.string.no_name)
        }
        val interpolation = if (article.collect) 1F else 0F
        updateCardViewTopLeftCornerSize(interpolation)

        binding.executePendingBindings()
    }


    override fun onReboundOffsetChanged(
        currentSwipePercentage: Float,
        swipeThreshold: Float,
        currentTargetHasMetThresholdOnce: Boolean
    ) {
        if (currentTargetHasMetThresholdOnce) return

        val isCollected = binding.article?.collect ?: false

        val interpolation = (currentSwipePercentage / swipeThreshold).coerceIn(0F, 1F)
        val adjustedInterpolation = abs((if (isCollected) 1F else 0F) - interpolation)
        updateCardViewTopLeftCornerSize(adjustedInterpolation)

        val thresholdMet = currentSwipePercentage >= swipeThreshold
        val shouldStar = when {
            thresholdMet && isCollected -> false
            thresholdMet && !isCollected -> true
            else -> return
        }
        binding.root.isActivated = shouldStar
    }

    override fun onRebounded() {
        val article = binding.article ?: return
        binding.listener?.onArticleStarChanged(article, !article.collect)
    }

    private fun updateCardViewTopLeftCornerSize(interpolation: Float) {
        binding.cardView.apply {
            shapeAppearanceModel = shapeAppearanceModel.toBuilder()
                .setTopLeftCornerSize(interpolation * starredCornerSize)
                .build()
        }
    }
}