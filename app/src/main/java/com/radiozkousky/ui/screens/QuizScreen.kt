package com.radiozkousky.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radiozkousky.data.Category
import com.radiozkousky.ui.theme.*
import com.radiozkousky.viewmodel.LearningViewModel
import com.radiozkousky.viewmodel.QuizState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    viewModel: LearningViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.quizState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kvíz") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Zpět")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        if (state.isFinished) {
            FinishedContent(
                title = "Kvíz dokončen!",
                subtitle = "Správně: ${state.correctCount} / ${state.totalAnswered}",
                onBack = onBack,
                modifier = Modifier.padding(padding),
                extraContent = {
                    Spacer(modifier = Modifier.height(16.dp))
                    QuizResultBar(
                        correct = state.correctCount,
                        incorrect = state.incorrectCount
                    )
                }
            )
        } else {
            QuizContent(
                state = state,
                onSelectOption = { viewModel.selectQuizOption(it) },
                onNext = { viewModel.nextQuizQuestion() },
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
fun QuizContent(
    state: QuizState,
    onSelectOption: (Int) -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val question = state.currentQuestion ?: return

    val categoryColor = when (question.category) {
        Category.PREDPISY -> CategoryPredpisyColor
        Category.PROVOZ -> CategoryProvozColor
        Category.ELEKTRO -> CategoryElektroColor
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LinearProgressIndicator(
            progress = { state.progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = categoryColor,
            trackColor = categoryColor.copy(alpha = 0.2f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = question.category.shortTitle,
                color = categoryColor,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "${state.correctCount} ✓",
                    color = CorrectGreen,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "${state.incorrectCount} ✗",
                    color = IncorrectRed,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${state.currentIndex + 1} / ${state.questions.size}",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = categoryColor.copy(alpha = 0.08f)
            )
        ) {
            Text(
                text = question.text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(20.dp),
                textAlign = TextAlign.Start
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            state.options.forEachIndexed { index, option ->
                val isSelected = state.selectedOptionIndex == index
                val backgroundColor by animateColorAsState(
                    targetValue = when {
                        !state.isAnswered -> MaterialTheme.colorScheme.surface
                        option.isCorrect -> CorrectGreen.copy(alpha = 0.15f)
                        isSelected && !option.isCorrect -> IncorrectRed.copy(alpha = 0.15f)
                        else -> MaterialTheme.colorScheme.surface
                    },
                    label = "optionBg"
                )
                val borderColor by animateColorAsState(
                    targetValue = when {
                        !state.isAnswered && isSelected -> categoryColor
                        !state.isAnswered -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        option.isCorrect -> CorrectGreen
                        isSelected && !option.isCorrect -> IncorrectRed
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    },
                    label = "optionBorder"
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = backgroundColor),
                    border = BorderStroke(2.dp, borderColor),
                    onClick = { if (!state.isAnswered) onSelectOption(index) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        val letterLabel = ('A' + index).toString()
                        Text(
                            text = "$letterLabel)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = borderColor
                        )
                        Text(
                            text = option.text,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f)
                        )
                        if (state.isAnswered) {
                            if (option.isCorrect) {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    contentDescription = "Správně",
                                    tint = CorrectGreen
                                )
                            } else if (isSelected) {
                                Icon(
                                    Icons.Default.Cancel,
                                    contentDescription = "Špatně",
                                    tint = IncorrectRed
                                )
                            }
                        }
                    }
                }
            }
        }

        if (state.isAnswered) {
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Další otázka",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }
        }
    }
}

@Composable
fun QuizResultBar(correct: Int, incorrect: Int) {
    val total = correct + incorrect
    if (total == 0) return
    val percentage = (correct.toFloat() / total * 100).toInt()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$percentage % úspěšnost",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = if (percentage >= 90) CorrectGreen else IncorrectRed
        )
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { correct.toFloat() / total },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(12.dp)
                .clip(RoundedCornerShape(6.dp)),
            color = CorrectGreen,
            trackColor = IncorrectRed.copy(alpha = 0.3f)
        )
    }
}
