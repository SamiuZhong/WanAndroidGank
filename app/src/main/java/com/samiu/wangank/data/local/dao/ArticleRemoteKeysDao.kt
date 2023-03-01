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
    suspend fun insertAll(remoteKeys: List<ArticleRemoteKeys>)

    @Query("SELECT * from article_remote_keys_table WHERE articleId = :articleId")
    suspend fun findRemoteKeyByArticleId(articleId: Int): ArticleRemoteKeys

    @Query("DELETE FROM article_remote_keys_table WHERE articleId = :articleId")
    suspend fun deleteRemoteKeyByArticleId(articleId: Int)

    /**
     * 删除全部数据
     */
    @Query("DELETE FROM article_remote_keys_table")
    suspend fun clearAll()

    /**
     * 删除首页数据
     */
    @Query("DELETE FROM article_remote_keys_table WHERE chapterName='自助'")
    suspend fun clearFrontKeys()

    /**
     * 删除广场数据
     */
    @Query("DELETE FROM article_remote_keys_table WHERE chapterName='广场'")
    suspend fun clearSquareKeys()

    /**
     * 根据cid删除数据
     */
    @Query("DELETE FROM article_remote_keys_table WHERE chapterId=:cid")
    suspend fun clearKeysWithCid(cid:Int)
}