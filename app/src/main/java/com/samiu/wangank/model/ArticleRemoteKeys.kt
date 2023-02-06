package com.samiu.wangank.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samiu.wangank.utils.Constants

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@Entity(tableName = Constants.Database.ARTICLE_REMOTE_KEYS_TABLE)
data class ArticleRemoteKeys(
    @PrimaryKey
    val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)
