package com.samiu.wangank.ui.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ItemWanProjectBinding
import com.samiu.wangank.model.bean.Article
import com.samiu.wangank.ui.adapter.drawable.ArticleSwipeActionDrawable
import com.samiu.wangank.ui.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.adapter.WanArticleAdapter
import kotlin.math.abs

/**
 * @author Samiu 2020/7/6
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class ProjectViewHolder(
    private val binding: ItemWanProjectBinding,
    listener: WanArticleAdapter.ArticleListener
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
        binding.projectAuthor.text =
            when {
                !article.author.isNullOrEmpty() -> article.author
                !article.shareUser.isNullOrEmpty() -> article.shareUser
                else -> "佚名"
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