// MainActivity.kt
package com.example.bottomnavigationnewsbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bottomnavigationnewsbar.components.BottomNavigationBar
import com.example.bottomnavigationnewsbar.navigation.ScreenNews
import com.example.bottomnavigationnewsbar.repository.NewsRepository
import com.example.bottomnavigationnewsbar.screens.AllNewsScreen
import com.example.bottomnavigationnewsbar.screens.ArticleScreen
import com.example.bottomnavigationnewsbar.screens.BreakingNewsScreen
import com.example.bottomnavigationnewsbar.screens.SavedNewsScreen
import com.example.bottomnavigationnewsbar.ui.theme.BottomNavigationNewsBarTheme
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModel
import com.example.bottomnavigationnewsbar.viewmodels.NewsViewModelProviderFactory
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = NewsRepository(applicationContext)
        val factory = NewsViewModelProviderFactory(repository)

        setContent {
            BottomNavigationNewsBarTheme {
                val vm: NewsViewModel = viewModel(factory = factory)
                MainScreen(vm)
            }
        }
    }
}

@Composable
fun MainScreen(vm: NewsViewModel) {
    val nav = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(nav) }) { p ->
        NavHost(
            navController = nav,
            startDestination = ScreenNews.BreakingNews.route,
            modifier = Modifier.padding(p)
        ) {
            composable(ScreenNews.BreakingNews.route) {
                BreakingNewsScreen(nav, vm)
            }
            composable(ScreenNews.AllNews.route) {
                AllNewsScreen(nav, vm)
            }
            composable(ScreenNews.SavedNews.route) {
                SavedNewsScreen(nav, vm)
            }
            composable(
                "${ScreenNews.Article.route}?articleJson={articleJson}",
                arguments = listOf(navArgument("articleJson") { type = NavType.StringType })
            ) { e ->
                e.arguments?.getString("articleJson")?.let { json ->
                    val art = Gson().fromJson(json, com.example.bottomnavigationnewsbar.models.Article::class.java)
                    ArticleScreen(article = art, newsViewModel = vm)
                }
            }
        }
    }
}
