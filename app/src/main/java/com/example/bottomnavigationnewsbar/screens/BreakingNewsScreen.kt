// BreakingNewsScreen.kt
package com.example.bottomnavigationnewsbar.screens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bottomnavigationnewsbar.components.ArticleItem
import com.example.bottomnavigationnewsbar.models.Article
import com.example.bottomnavigationnewsbar.navigation.ScreenNews
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModel
import com.google.gson.Gson

@Composable
fun BreakingNewsScreen(nav: NavController, vm: NewsViewModel) {
    val resp by vm.breakingNews.observeAsState()
    if (resp == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        val list: List<Article> = resp!!.articles
        if (list.isEmpty()) Text("Aucun article") else {
            LazyColumn {
                items(list) { art ->
                    ArticleItem(art) {
                        val js = Uri.encode(Gson().toJson(art))
                        nav.navigate("${ScreenNews.Article.route}?articleJson=$js")
                    }
                }
            }
        }
    }
}
