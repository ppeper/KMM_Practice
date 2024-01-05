package com.ppeper.dailypulse.android

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ppeper.dailypulse.android.screens.ArticleScreen
import com.ppeper.dailypulse.android.screens.InfoScreen
import com.ppeper.dailypulse.android.screens.Screen
import com.ppeper.dailypulse.article.ArticleViewModel

@Composable
fun AppScaffold(
    articleViewModel: ArticleViewModel
) {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            navController = navController,
            articleViewModel
        )
    }
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    articleViewModel: ArticleViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.ARTICLES.route
    ) {
        composable(Screen.ARTICLES.route) {
            ArticleScreen(
                onInfoButtonClick = {
                    navController.navigate(Screen.INFO.route)
                },
                articleViewModel = articleViewModel
            )
        }
        composable(Screen.INFO.route) {
            InfoScreen {
                navController.popBackStack()
            }
        }
    }
}