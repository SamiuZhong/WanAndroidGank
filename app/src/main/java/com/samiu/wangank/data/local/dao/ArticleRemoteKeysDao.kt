package com.samiu.wangank.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samiu.wangank.model.ArticleRemoteKeys

/**
 * @author samiu 2022/11/28
 * @email samiuzhong@outlook.com
 */
@Dao
interface ArticleRemoteKeysDao {

    @Query("SELECT * FROM article_remote_keys_table WHERE id = :id")
    suspend fun getRemoteKeys(id: Int): ArticleRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<ArticleRemoteKeys>)

    @Query("DELETE FROM article_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}