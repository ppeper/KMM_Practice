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



class ArticleViewModel(
    private val useCase: ArticleUseCase
): BaseViewModel() {

    private val _articleState = MutableStateFlow(ArticleState(loading = true))
    val articleState: StateFlow<ArticleState>
        get() = _articleState

    init {
        getArticles()
    }

    private fun getArticles(
        forceFetch: Boolean = false
    ) {
        scope.launch {
            delay(500)
            val fetchArticles = useCase.getArticles(forceFetch)
            _articleState.emit(ArticleState(articles = fetchArticles))
        }
    }
}