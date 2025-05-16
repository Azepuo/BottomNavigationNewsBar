package com.example.bottomnavigationnewsbar.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnavigationnewsbar.repository.NewsRepository


class NewsViewModelProviderFactory(
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    // La méthode create() est appelée automatiquement par ViewModelProvider
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            // Si la classe demandée est NewsViewModel, on la crée avec le repository
            NewsViewModel(newsRepository) as T
        } else {
            // Sinon, on lance une erreur
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}