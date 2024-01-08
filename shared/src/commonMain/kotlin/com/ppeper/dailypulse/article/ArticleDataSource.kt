package com.ppeper.dailypulse.article

import ppeper.dailypulse.db.DailyPulseDB

class ArticleDataSource(
    private val database: DailyPulseDB
) {

    fun getAllArticles(): List<ArticleRaw> =
        database.dailyPulseDBQueries.selectAllArticles(::mapToArticle).executeAsList()

    fun insertArticles(articles: List<ArticleRaw>) {
        database.dailyPulseDBQueries.transaction {
            articles.forEach { article ->
                insertArticle(article)
            }
        }
    }

    fun clearArticles() = database.dailyPulseDBQueries.removeAllArticles()

    private fun insertArticle(article: ArticleRaw) = with(article) {
        database.dailyPulseDBQueries.insertArticle(
            title, desc, date, imageUrl
        )
    }

    private fun mapToArticle(
        title: String,
        desc: String?,
        date: String,
        url: String?
    ) = ArticleRaw(
        title = title,
        desc = desc,
        date = date,
        imageUrl = url
    )
}