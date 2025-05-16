package com.example.bottomnavigationnewsbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.example.bottomnavigationnewsbar.components.BottomNavigationBar
import com.example.bottomnavigationnewsbar.models.Article
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
        val newsRepository = NewsRepository()
        val viewModelFactory = NewsViewModelProviderFactory(newsRepository)

        setContent {
            val newsViewModel: NewsViewModel = viewModel(factory = viewModelFactory)
            MainScreen(newsViewModel)
        }
    }
}

@Composable
fun MainScreen(newsViewModel: NewsViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNews.BreakingNews.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNews.BreakingNews.route) {
                BreakingNewsScreen(navController, newsViewModel)
            }
            composable(ScreenNews.AllNews.route) {
                AllNewsScreen(navController, newsViewModel)
            }
            composable(ScreenNews.SavedNews.route) {
                SavedNewsScreen(navController)
            }
            composable(
                route = "${ScreenNews.Article.route}/{articleUrl}",
                arguments = listOf(navArgument("articleUrl") { type = NavType.StringType })
            ) { backStackEntry ->
                val articleUrl = backStackEntry.arguments?.getString("articleUrl")
                articleUrl?.let {
                    ArticleScreen(it) // On passe directement l’URL à ArticleScreen
                }
            }

        }
    }
}