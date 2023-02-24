package com.samiu.wangank.ui.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.samiu.wangank.data.remote.BannerList
import com.samiu.wangank.model.BannerDTO
import com.youth.banner.adapter.BannerAdapter

/**
 * @author samiu 2023/2/24
 * @email samiuzhong@outlook.com
 */
class BannerAdapter(var list: BannerList) : BannerAdapter<BannerDTO, ImageBannerHolder>(list) {

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImageBannerHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        return ImageBannerHolder(imageView)
    }

    override fun onBindView(
        holder: ImageBannerHolder,
        data: BannerDTO?,
        position: Int,
        size: Int
    ) {
        holder.image.load(list[position].imagePath)
    }
}

class ImageBannerHolder(val image: ImageView) : RecyclerView.ViewHolder(image)