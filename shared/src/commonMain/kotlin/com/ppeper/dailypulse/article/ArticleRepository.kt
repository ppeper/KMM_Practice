package com.ppeper.dailypulse.article

class ArticleRepository(
    private val dataSource: ArticleDataSource,
    private val service: ArticleService
) {

    suspend fun getArticles(
        forceFetch: Boolean
    ): List<ArticleRaw> {
        if (forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }
        val articlesDb = dataSource.getAllArticles()

        if (articlesDb.isEmpty()) {
            fetchArticles()
        }
        return articlesDb
    }

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchArticles = service.fetchArticles()
        dataSource.insertArticles(fetchArticles)
        return fetchArticles
    }


}