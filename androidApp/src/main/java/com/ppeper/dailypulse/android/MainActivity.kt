package com.ppeper.dailypulse.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ppeper.dailypulse.Platform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // expect로 생성한 class와 실제로 actual 클래스로 상속받은 android 로직
        Platform().logSystemInfo()

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InfoScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        InfoScreen()
    }
}
