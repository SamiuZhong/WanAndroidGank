package com.samiu.wangank.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samiu.wangank.data.local.dao.ArticleDao
import com.samiu.wangank.data.local.dao.ArticleRemoteKeysDao
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.ArticleRemoteKeys

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@Database(entities = [ArticleDTO::class, ArticleRemoteKeys::class], version = 1)
abstract class WanDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun articleRemoteKeysDao(): ArticleRemoteKeysDao
}