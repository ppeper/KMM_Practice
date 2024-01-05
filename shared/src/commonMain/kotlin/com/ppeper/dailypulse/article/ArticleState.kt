package com.ppeper.dailypulse.article

data class ArticleState (
    val articles: List<Article> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
