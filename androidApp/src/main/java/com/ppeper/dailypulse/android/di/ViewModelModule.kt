package com.ppeper.dailypulse.android.di

import com.ppeper.dailypulse.article.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ArticleViewModel(get())
    }
}