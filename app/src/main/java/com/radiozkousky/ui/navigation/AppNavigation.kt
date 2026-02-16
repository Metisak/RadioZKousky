package com.radiozkousky.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.radiozkousky.data.Category
import com.radiozkousky.ui.screens.*
import com.radiozkousky.viewmodel.LearningViewModel

object Routes {
    const val HOME = "home"
    const val FLASHCARDS = "flashcards"
    const val QUIZ = "quiz"
    const val TEST = "test"
    const val SPACED = "spaced"
    const val STATS = "stats"
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: LearningViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onNavigateToFlashcards = { category ->
                    viewModel.startFlashcards(category)
                    val categoryArg = category?.name ?: "ALL"
                    navController.navigate("${Routes.FLASHCARDS}/$categoryArg")
                },
                onNavigateToQuiz = { category ->
                    viewModel.startQuiz(category)
                    val categoryArg = category?.name ?: "ALL"
                    navController.navigate("${Routes.QUIZ}/$categoryArg")
                },
                onNavigateToTest = {
                    viewModel.startTest()
                    navController.navigate(Routes.TEST)
                },
                onNavigateToSpaced = { category ->
                    viewModel.startSpacedRepetition(category)
                    val categoryArg = category?.name ?: "ALL"
                    navController.navigate("${Routes.SPACED}/$categoryArg")
                },
                onNavigateToStats = {
                    navController.navigate(Routes.STATS)
                }
            )
        }

        composable(
            route = "${Routes.FLASHCARDS}/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            FlashcardScreen(
                viewModel = viewModel,
                isSpacedRepetition = false,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.QUIZ}/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            QuizScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.TEST) {
            TestScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.SPACED}/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            FlashcardScreen(
                viewModel = viewModel,
                isSpacedRepetition = true,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.STATS) {
            StatsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
