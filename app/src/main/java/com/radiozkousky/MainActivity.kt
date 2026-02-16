package com.radiozkousky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.radiozkousky.ui.navigation.AppNavigation
import com.radiozkousky.ui.theme.RadioZKouskyTheme
import com.radiozkousky.viewmodel.LearningViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RadioZKouskyTheme {
                val navController = rememberNavController()
                val viewModel: LearningViewModel = viewModel()
                AppNavigation(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}
