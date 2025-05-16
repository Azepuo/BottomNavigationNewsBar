package com.example.bottomnavigationnewsbar.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity)

    @Query("DELETE FROM articles WHERE url = :url")
    suspend fun deleteByUrl(url: String)

    @Query("SELECT * FROM articles")
    fun getAllFavorites(): Flow<List<ArticleEntity>>
}
