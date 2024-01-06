package com.ppeper.dailypulse.di

import com.ppeper.dailypulse.article.di.articleModule

val sharedKoinModule = listOf(
    articleModule,
    networkModule
)