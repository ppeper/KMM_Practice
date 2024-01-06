package com.ppeper.dailypulse.article.di

import com.ppeper.dailypulse.article.ArticleService
import com.ppeper.dailypulse.article.ArticleUseCase
import com.ppeper.dailypulse.article.ArticleViewModel
import org.koin.dsl.module

val articleModule = module {

    single { ArticleService(get()) }
    single { ArticleUseCase(get()) }
    single { ArticleViewModel(get()) }
}