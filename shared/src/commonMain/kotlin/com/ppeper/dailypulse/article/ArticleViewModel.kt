package com.ppeper.dailypulse.article

import com.ppeper.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleViewModel: BaseViewModel() {

    private val _articleState = MutableStateFlow(ArticleState(loading = true))
    val articleState: StateFlow<ArticleState>
        get() = _articleState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(500)
            _articleState.emit(ArticleState())
        }
    }
}