package com.samiu.wangank.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samiu.wangank.util.Constants.ARTICLE_REMOTE_KEYS_TABLE

/**
 * @author samiu 2022/11/28
 * @email samiuzhong@outlook.com
 */
@Entity(tableName = ARTICLE_REMOTE_KEYS_TABLE)
data class ArticleRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)