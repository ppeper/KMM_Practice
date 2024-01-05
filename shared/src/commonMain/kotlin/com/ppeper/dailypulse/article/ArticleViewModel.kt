package com.ppeper.dailypulse.article

import com.ppeper.dailypulse.BaseViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticleViewModel: BaseViewModel() {

    private val useCase: ArticleUseCase

    private val _articleState = MutableStateFlow(ArticleState(loading = true))
    val articleState: StateFlow<ArticleState>
        get() = _articleState

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        useCase = ArticleUseCase(ArticleService(httpClient))
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(500)
            val fetchArticles = useCase.getArticles()
            _articleState.emit(ArticleState(articles = fetchArticles))
        }
    }
}