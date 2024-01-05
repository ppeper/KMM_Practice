package com.ppeper.dailypulse.article

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(
    private val httpClient: HttpClient
) {
    private val country = "kr"
    private val category = "technology"
    private val apiKey = "6275fc4c74974562a492579b8ba45118"
    private val BASE_URL = "https://newsapi.org/v2/"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticleResponse = httpClient
            .get(BASE_URL + "top-headlines?country=$country&category=$category&apiKey=$apiKey").body()
        return response.articles
    }
}