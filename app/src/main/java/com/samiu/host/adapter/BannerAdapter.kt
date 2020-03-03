package com.samiu.host.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samiu.host.model.bean.wan.Banner
import com.youth.banner.adapter.BannerAdapter

/**
 * @author Samiu 2020/3/3
 */
class ImageBannerAdapter(var list: List<Banner>) : BannerAdapter<Banner, ImageBannerHolder>(list) {
    private lateinit var context: Context

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ImageBannerHolder {
        parent?.context?.let {
            context = it
        }
        val imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return ImageBannerHolder(imageView)
    }

    override fun onBindView(holder: ImageBannerHolder?, data: Banner?, position: Int, size: Int) {
        if (holder is ImageBannerHolder)
            Glide.with(context).load(list[position].imagePath).into(holder.image)
    }
}

class ImageBannerHolder(var image: ImageView) : RecyclerView.ViewHolder(image)