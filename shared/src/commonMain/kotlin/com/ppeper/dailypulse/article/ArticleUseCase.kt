package com.ppeper.dailypulse.article

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticleUseCase(
    private val articleService: ArticleService
) {
    suspend fun getArticles(): List<Article> {
        return articleService.fetchArticles().map {
            Article(
                title = it.title,
                desc = it.desc ?: "클릭하여 보기",
                date = getDayAgoString(it.date),
                imageUrl = it.imageUrl ?: ""
            )
        }
    }

    private fun getDayAgoString(
        date: String
    ): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
        return when {
            1 < abs(days) -> "${abs(days)} 일전"
            1 == abs(days) -> "어제"
            else -> "오"
        }
    }

}