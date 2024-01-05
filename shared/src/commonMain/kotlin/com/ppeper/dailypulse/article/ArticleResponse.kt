package com.ppeper.dailypulse.article

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleRaw>
)