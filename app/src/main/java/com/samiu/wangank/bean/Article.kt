package com.samiu.wangank.bean

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
data class Article(
    val id: Int,
    val originId: Int,
    val title: String,
    val chapterId: Int,
    val chapterName: String,
    val envelopePic: String,
    val link: String,
    val author: String,
    val origin: String,
    val publishTime: Long,
    val zan: Int,
    val desc: String,
    val visible: Int,
    val niceDate: String,
    val niceShareDate: String,
    val courseId: Int,
    var collect: Boolean,
    val apkLink: String,
    val projectLink: String,
    val superChapterId: Int,
    val superChapterName: String?,
    val type: Int,
    val fresh: Boolean,
    val audit: Int,
    val prefix: String,
    val selfVisible: Int,
    val shareDate: Long,
    val shareUser: String,
    val tags: Any,
    val userId: Int
)

object ArticleDiff:DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article) =
        oldItem.id==newItem.id

    override fun areContentsTheSame(oldItem: Article, newItem: Article)=
        oldItem.link==newItem.link
}