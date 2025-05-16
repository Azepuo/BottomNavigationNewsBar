// ArticleScreen.kt
package com.example.bottomnavigationnewsbar.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.livedata.observeAsState
import com.example.bottomnavigationnewsbar.models.Article
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite

@Composable
fun ArticleScreen(article: Article, newsViewModel: NewsViewModel) {
    val ctx = LocalContext.current
    val favs by newsViewModel.savedArticles.observeAsState(initial = emptyList())
    val isFav = favs.any { it.url == article.url }

    Box(Modifier.fillMaxSize()) {
        AndroidView({ ctx ->
            WebView(ctx).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(article.url)
            }
        }, modifier = Modifier.fillMaxSize())

        FloatingActionButton(
            onClick = {
                if (isFav) newsViewModel.deleteArticle(article.url)
                else       newsViewModel.saveArticle(article)
                Toast.makeText(
                    ctx,
                    if (isFav) "Retiré des favoris" else "Ajouté aux favoris",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp),
            containerColor = if (isFav) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Default.Favorite, contentDescription = null)
        }
    }
}
