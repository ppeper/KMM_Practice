//
//  ArticleScreen.swift
//  iosApp
//
//  Created by 박준후 on 1/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

extension ArticleScreen {
    
    @MainActor
    class ArticleViewModelWrapper: ObservableObject {
        let articleViewModel: ArticleViewModel
        @Published var articleState: ArticleState
        
        init() {
            articleViewModel = ArticleInjector().articleViewModel
            articleState = articleViewModel.articleState.value
        }
        
        func startObserving() {
            Task {
                for await articleS in articleViewModel.articleState {
                    self.articleState = articleS
                }
            }
        }
    }
}

struct ArticleScreen: View {
    @ObservedObject private(set) var viewModel: ArticleViewModelWrapper
    var body: some View {
        VStack {
            AppBar()
            
            if viewModel.articleState.loading {
                Loader()
            }
            if let error = viewModel.articleState.error {
                ErrorMessage(message: error)
            }
            if (!viewModel.articleState.articles.isEmpty) {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.articleState.articles, id: \.self) {
                            article in ArticleItemView(article: article)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct AppBar: View {
    var body: some View {
        Text("기사")
            .font(.largeTitle)
            .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
    }
}

struct ArticleItemView: View {
    var article: Article
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            AsyncImage(url: URL(string: article.imageUrl)) { phase in
                if phase.image != nil {
                    phase.image!
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                } else if phase.error != nil {
                    Text("이미지 로딩 실패")
                } else {
                    ProgressView()
                }
            }
            Text(article.title)
                .font(.title)
                .fontWeight(.bold)
            Text(article.desc)
            Text(article.date)
                .frame(maxWidth: .infinity, alignment: .trailing)
                .foregroundStyle(.gray)
        }
        .padding(16)
    }
}

struct Loader: View {
    var body: some View {
        ProgressView()
    }
}

struct ErrorMessage: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(.title)
    }
}
