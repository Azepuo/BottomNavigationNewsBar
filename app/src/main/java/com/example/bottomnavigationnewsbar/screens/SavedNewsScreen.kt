// SavedNewsScreen.kt
package com.example.bottomnavigationnewsbar.screens

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bottomnavigationnewsbar.components.ArticleItem
import com.example.bottomnavigationnewsbar.data.toArticle
import com.example.bottomnavigationnewsbar.navigation.ScreenNews
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModel
import com.google.gson.Gson

@Composable
fun SavedNewsScreen(nav: NavController, vm: NewsViewModel) {
    val favs by vm.savedArticles.observeAsState(initial = emptyList())
    LazyColumn(Modifier.fillMaxSize()) {
        items(favs) { ent ->
            val art = ent.toArticle()
            ArticleItem(art) {
                val js = Uri.encode(Gson().toJson(art))
                nav.navigate("${ScreenNews.Article.route}?articleJson=$js")
            }
        }
    }
}
