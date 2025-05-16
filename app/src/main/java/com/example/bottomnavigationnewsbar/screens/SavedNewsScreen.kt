package com.example.bottomnavigationnewsbar.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bottomnavigationnewsbar.navigation.ScreenNews

@Composable
fun SavedNewsScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Saved News")
        Button(onClick = {
            navController.navigate(ScreenNews.Article.route)
        }) {
            Text("Voir l'article")
        }
    }
}
