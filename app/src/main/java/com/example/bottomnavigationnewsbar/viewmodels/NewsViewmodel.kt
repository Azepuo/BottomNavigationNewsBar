package com.example.bottomnavigationnewsbar.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationnewsbar.models.NewsResponse
import com.example.bottomnavigationnewsbar.repository.NewsRepository
import kotlinx.coroutines.launch


class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<NewsResponse> = MutableLiveData()
    val searchNews: MutableLiveData<NewsResponse> = MutableLiveData() // <-- ajouter ceci

    init {
        getBreakingNews("us")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        try {
            val response = newsRepository.getBreakingNews(countryCode)
            breakingNews.postValue(response.body())
        } catch (e: Exception) {
            // Gérer l'erreur ici si besoin
        }
    }

    fun getSearchNews(query: String) = viewModelScope.launch {
        try {
            val response = newsRepository.getSearchNews(query)
            searchNews.postValue(response.body())
        } catch (e: Exception) {
            // Gérer l'erreur ici si besoin
        }
    }
}