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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radiozkousky.data.Category
import com.radiozkousky.ui.theme.*
import com.radiozkousky.viewmodel.LearningViewModel
import com.radiozkousky.viewmodel.TestResult
import com.radiozkousky.viewmodel.TestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(
    viewModel: LearningViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.testState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Simulace testu") },
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
        if (state.isFinished && state.result != null) {
            TestResultContent(
                result = state.result!!,
                onBack = onBack,
                modifier = Modifier.padding(padding)
            )
        } else {
            TestQuestionContent(
                state = state,
                onSelectOption = { viewModel.selectTestOption(it) },
                onNext = { viewModel.nextTestQuestion() },
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
fun TestQuestionContent(
    state: TestState,
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
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
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
            Text(
                text = "${state.currentIndex + 1} / ${state.questions.size}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
        }

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
                    label = "testOptionBg"
                )
                val borderColor by animateColorAsState(
                    targetValue = when {
                        !state.isAnswered && isSelected -> categoryColor
                        !state.isAnswered -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        option.isCorrect -> CorrectGreen
                        isSelected && !option.isCorrect -> IncorrectRed
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    },
                    label = "testOptionBorder"
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
                    text = if (state.currentIndex + 1 >= state.questions.size) "Vyhodnotit test"
                           else "Další otázka",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    if (state.currentIndex + 1 >= state.questions.size) Icons.Default.Done
                    else Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun TestResultContent(
    result: TestResult,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = if (result.passed) Icons.Default.EmojiEvents else Icons.Default.Warning,
            contentDescription = null,
            tint = if (result.passed) CorrectGreen else IncorrectRed,
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (result.passed) "SPLNĚNO!" else "NESPLNĚNO",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = if (result.passed) CorrectGreen else IncorrectRed,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (result.passed) "Gratulujeme! Splnil bys zkoušku."
                   else "Pro splnění je potřeba alespoň 90 % v každém předmětu.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Výsledky podle předmětu",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        result.categoryResults.forEach { (category, pair) ->
            val (correct, total) = pair
            val percentage = if (total > 0) (correct.toDouble() / total * 100).toInt() else 0
            val passed = percentage >= 90
            val categoryColor = when (category) {
                Category.PREDPISY -> CategoryPredpisyColor
                Category.PROVOZ -> CategoryProvozColor
                Category.ELEKTRO -> CategoryElektroColor
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (passed) CorrectGreen.copy(alpha = 0.08f)
                                     else IncorrectRed.copy(alpha = 0.08f)
                ),
                border = BorderStroke(
                    1.dp,
                    if (passed) CorrectGreen.copy(alpha = 0.3f) else IncorrectRed.copy(alpha = 0.3f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = category.shortTitle,
                            fontWeight = FontWeight.Bold,
                            color = categoryColor
                        )
                        Text(
                            text = "$correct / $total správně",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "$percentage %",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = if (passed) CorrectGreen else IncorrectRed
                        )
                        Icon(
                            imageVector = if (passed) Icons.Default.CheckCircle else Icons.Default.Cancel,
                            contentDescription = null,
                            tint = if (passed) CorrectGreen else IncorrectRed
                        )
                    }
                }
            }
        }

        if (result.wrongAnswers.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Chybné odpovědi (${result.wrongAnswers.size})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            result.wrongAnswers.forEach { (question, givenAnswer) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = question.text,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(
                                Icons.Default.Cancel,
                                contentDescription = null,
                                tint = IncorrectRed,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = givenAnswer,
                                color = IncorrectRed,
                                fontSize = 13.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.Top) {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = CorrectGreen,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = question.answer,
                                color = CorrectGreen,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onBack,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Zpět na hlavní menu", fontSize = 16.sp)
        }
    }
}
