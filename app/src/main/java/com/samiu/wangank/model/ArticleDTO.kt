package com.samiu.wangank.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samiu.wangank.util.Constants.ARTICLE_TABLE

/**
 * 首页文章列表
 *
 * @author samiu 2022/11/26
 * @email samiuzhong@outlook.com
 */
@Entity(tableName = ARTICLE_TABLE)
data class ArticleDTO(
    val adminAdd: Boolean = false,
    val apkLink: String = "",
    val audit: Int = 0,
    val author: String = "",
    val canEdit: Boolean = false,
    val chapterId: Int = 0,
    val chapterName: String = "",
    val collect: Boolean = false,
    val courseId: Int = 0,
    val desc: String = "",
    val descMd: String = "",
    val envelopePic: String = "",
    val fresh: Boolean = false,
    val host: String = "",
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val isAdminAdd: Boolean = false,
    val link: String = "",
    val niceDate: String = "",
    val niceShareDate: String = "",
    val origin: String = "",
    val prefix: String = "",
    val projectLink: String = "",
    val publishTime: Long = 0,
    val realSuperChapterId: Int = 0,
    val selfVisible: Int = 0,
    val shareDate: Long = 0,
    val shareUser: String = "",
    val superChapterId: Int = 0,
    val superChapterName: String = "",
    @Embedded
    val tags: List<TagDTO> = listOf(),
    val title: String = "",
    val type: Int = 0,
    val userId: Int = 0,
    val visible: Int = 0,
    val zan: Int = 0
)

data class TagDTO(
    val name: String = "",
    val url: String = ""
)