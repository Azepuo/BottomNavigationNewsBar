package com.example.bottomnavigationnewsbar.data

import com.example.bottomnavigationnewsbar.models.Article
import com.example.bottomnavigationnewsbar.models.Source

// Extension pour sauvegarder
fun Article.toEntity() = ArticleEntity(
    url = url,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceName = source.name,
    title = title,
    urlToImage = urlToImage
)

// Extension pour afficher
fun ArticleEntity.toArticle() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = Source(id = null, name = sourceName),
    title = title,
    url = url,
    urlToImage = urlToImage
)
