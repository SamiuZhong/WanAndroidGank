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

    @Query("SELECT * FROM article_table")
    fun getAllArticles(): PagingSource<Int, ArticleDTO>

    @Query("DELETE FROM article_table")
    suspend fun clearAll()
}