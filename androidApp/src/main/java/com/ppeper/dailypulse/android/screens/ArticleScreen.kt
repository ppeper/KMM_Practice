package com.ppeper.dailypulse.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ppeper.dailypulse.article.Article
import com.ppeper.dailypulse.article.ArticleViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticleScreen(
    onInfoButtonClick: () -> Unit,
    articleViewModel: ArticleViewModel = getViewModel()
) {
    val articleState = articleViewModel.articleState.collectAsStateWithLifecycle()

    Column {
        AppBar(onInfoButtonClick)
        with(articleState.value) {
            if (loading) {
                Loader()
            }
            if (articles.isNotEmpty()) {
                ArticleListView(articleViewModel.articleState.value.articles)
            }
            error?.let {
                ErrorMessage(it)
            }
        }
    }
}

@Composable
private fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun ErrorMessage(
    message: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onInfoButtonClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "기사") },
        actions = {
            IconButton(
                onClick = onInfoButtonClick
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "정보"
                )
            }
        }
    )
}

@Composable
private fun ArticleListView(
    articles: List<Article>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(articles) { article ->
            ArticleItemView(article = article)
        }
    }
}

@Composable
private fun ArticleItemView(
    article: Article
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
