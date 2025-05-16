// AllNewsScreen.kt
package com.example.bottomnavigationnewsbar.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bottomnavigationnewsbar.components.ArticleItem
import com.example.bottomnavigationnewsbar.models.Article
import com.example.bottomnavigationnewsbar.navigation.ScreenNews
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModel
import com.google.gson.Gson

@Composable
fun AllNewsScreen(nav: NavController, vm: NewsViewModel) {
    val q = remember { mutableStateOf("maroc") }
    val res by vm.searchNews.observeAsState()

    Column {
        TextField(
            value = q.value,
            onValueChange = { q.value = it; vm.getSearchNews(it) },
            placeholder = { Text("Rechercher...") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        LazyColumn {
            items(res?.articles.orEmpty()) { art: Article ->
                ArticleItem(art) {
                    val js = Uri.encode(Gson().toJson(art))
                    nav.navigate("${ScreenNews.Article.route}?articleJson=$js")
                }
            }
        }
    }
}
