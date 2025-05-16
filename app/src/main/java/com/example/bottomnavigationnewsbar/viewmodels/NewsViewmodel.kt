package com.example.bottomnavigationnewsbar.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationnewsbar.data.ArticleEntity
import com.example.bottomnavigationnewsbar.models.NewsResponse
import com.example.bottomnavigationnewsbar.repository.NewsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews = MutableLiveData<NewsResponse>()
    val searchNews   = MutableLiveData<NewsResponse>()
    val savedArticles = MutableLiveData<List<ArticleEntity>>()

    init {
        getBreakingNews("us")
        observeFavorites()
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        try {
            val response = newsRepository.getBreakingNews(countryCode)
            breakingNews.postValue(response.body())
        } catch (e: Exception) { /* gérer */ }
    }

    fun getSearchNews(query: String) = viewModelScope.launch {
        try {
            val response = newsRepository.getSearchNews(query)
            searchNews.postValue(response.body())
        } catch (e: Exception) { /* gérer */ }
    }

    private fun observeFavorites() = viewModelScope.launch {
        newsRepository.getSavedArticles().collect { list ->
            savedArticles.postValue(list)
        }
    }

    fun saveArticle(article: com.example.bottomnavigationnewsbar.models.Article) = viewModelScope.launch {
        newsRepository.saveArticle(article)
    }

    fun deleteArticle(url: String) = viewModelScope.launch {
        newsRepository.deleteArticle(url)
    }
}
