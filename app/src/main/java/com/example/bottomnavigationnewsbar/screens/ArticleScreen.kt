package com.example.bottomnavigationnewsbar.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView



@Composable
fun ArticleScreen(articleUrl: String) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(articleUrl)
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        FloatingActionButton(
            onClick = {
                Toast.makeText(context, "Fonction favori non implémentée", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 8.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Default.Favorite, contentDescription = "Favori")
        }
    }
}



/*
@Composable
fun ArticleScreen(article: Article) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        // WebView plein écran
        AndroidView(
            factory = {
                WebView(it).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    article.url?.let { url -> loadUrl(url) }
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // FAB en bas à droite
        FloatingActionButton(
            onClick = { Toast.makeText(context, "Fonction favori non implémentée", Toast.LENGTH_SHORT).show() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 8.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Default.Favorite, contentDescription = "Favori")
        }
    }
}*/