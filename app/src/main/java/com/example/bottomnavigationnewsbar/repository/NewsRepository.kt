package com.example.bottomnavigationnewsbar.repository

import com.example.bottomnavigationnewsbar.data.ArticleDatabase
import com.example.bottomnavigationnewsbar.data.ArticleEntity
import com.example.bottomnavigationnewsbar.data.toEntity

import com.example.bottomnavigationnewsbar.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class NewsRepository(context: android.content.Context) {

    private val api = RetrofitInstance.retrofitService
    private val dao = ArticleDatabase(context).getArticleDao()

    // Appels réseau existants
    suspend fun getBreakingNews(countryCode: String) = api.getBreakingNews(countryCode)
    suspend fun getSearchNews(searchQuery: String) = api.getSearchNews(searchQuery)

    // Favoris
    suspend fun saveArticle(article: com.example.bottomnavigationnewsbar.models.Article) {
        dao.upsert(article.toEntity())
    }

    suspend fun deleteArticle(url: String) {
        dao.deleteByUrl(url)
    }

    fun getSavedArticles(): Flow<List<ArticleEntity>> = dao.getAllFavorites()

}
