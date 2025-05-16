package com.example.bottomnavigationnewsbar.screens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bottomnavigationnewsbar.components.ArticleItem
import com.example.bottomnavigationnewsbar.navigation.ScreenNews
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModel


@Composable
fun BreakingNewsScreen(navController: NavController, viewModel: NewsViewModel) {
    val newsResponse by viewModel.breakingNews.observeAsState()

    if (newsResponse != null) {
        val articles = newsResponse!!.articles
        if (articles.isNullOrEmpty()) {
            Text("Aucun article trouvé.")
        } else {
            LazyColumn {
                items(articles) { article ->
                    ArticleItem(article = article) {
                        val encodedUrl = Uri.encode(article.url)
                        navController.navigate("${ScreenNews.Article.route}/$encodedUrl")
                    }
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

}