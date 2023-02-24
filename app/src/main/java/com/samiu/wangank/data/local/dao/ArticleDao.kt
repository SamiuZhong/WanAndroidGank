package com.samiu.wangank.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samiu.wangank.model.ArticleDTO

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(article: List<ArticleDTO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleDTO)

    /**
     * 所有文章数据
     */
    @Query("SELECT * FROM article_table")
    fun getAllArticles(): PagingSource<Int, ArticleDTO>

    /**
     * 首页文章数据
     */
    @Query("SELECT * FROM article_table WHERE chapterName='自助'")
    fun getFrontArticles(): PagingSource<Int, ArticleDTO>

    /**
     * 广场文章数据
     */
    @Query("SELECT * FROM article_table WHERE chapterName='广场'")
    fun getSquareArticles(): PagingSource<Int, ArticleDTO>

    /**
     * 删除全部数据
     */
    @Query("DELETE FROM article_table")
    suspend fun clearAll()

    /**
     * 删除首页数据
     */
    @Query("DELETE FROM article_table WHERE chapterName='自助'")
    suspend fun clearFrontArticles()

    /**
     * 删除广场数据
     */
    @Query("DELETE FROM article_table WHERE chapterName='广场'")
    suspend fun clearSquareArticles()
}