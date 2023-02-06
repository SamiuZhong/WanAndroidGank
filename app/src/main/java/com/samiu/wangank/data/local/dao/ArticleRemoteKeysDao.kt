package com.samiu.wangank.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samiu.wangank.model.ArticleRemoteKeys

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@Dao
interface ArticleRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemote(remoteKeys: List<ArticleRemoteKeys>)

    @Query("SELECT * from article_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): ArticleRemoteKeys

    @Query("DELETE FROM article_remote_keys_table")
    suspend fun clearAll()
}