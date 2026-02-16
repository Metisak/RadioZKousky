package com.radiozkousky.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(
    viewModel: LearningViewModel,
    onBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.refreshStats()
    }

    val stats by viewModel.statsState.collectAsState()
    var showResetDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Statistiky") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Zpět")
                    }
                },
                actions = {
                    IconButton(onClick = { showResetDialog = true }) {
                        Icon(Icons.Default.DeleteForever, contentDescription = "Reset")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Overview card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Celkový přehled",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatBubble(
                            value = stats.totalAnswered.toString(),
                            label = "Zodpovězeno",
                            color = MaterialTheme.colorScheme.primary
                        )
                        StatBubble(
                            value = stats.masteredCount.toString(),
                            label = "Naučeno",
                            color = CorrectGreen
                        )
                        StatBubble(
                            value = "${stats.totalQuestions}",
                            label = "Celkem",
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    val masteredPercent = if (stats.totalQuestions > 0)
                        (stats.masteredCount.toFloat() / stats.totalQuestions) else 0f

                    LinearProgressIndicator(
                        progress = { masteredPercent },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        color = CorrectGreen,
                        trackColor = CorrectGreen.copy(alpha = 0.15f)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "${(masteredPercent * 100).toInt()} % naučeno",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Accuracy card
            if (stats.totalCorrect + stats.totalIncorrect > 0) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Přesnost odpovědí",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        val total = stats.totalCorrect + stats.totalIncorrect
                        val accuracy = if (total > 0) (stats.totalCorrect.toFloat() / total * 100).toInt() else 0

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "$accuracy %",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (accuracy >= 90) CorrectGreen else if (accuracy >= 70) CategoryElektroColor else IncorrectRed
                                )
                                Text("Úspěšnost", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "${stats.totalCorrect}",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CorrectGreen
                                )
                                Text("Správně", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "${stats.totalIncorrect}",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = IncorrectRed
                                )
                                Text("Špatně", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }

            // Category progress
            Text(
                text = "Progress podle kategorie",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            stats.categoryStats.forEach { (category, pair) ->
                val (mastered, total) = pair
                val categoryColor = when (category) {
                    Category.PREDPISY -> CategoryPredpisyColor
                    Category.PROVOZ -> CategoryProvozColor
                    Category.ELEKTRO -> CategoryElektroColor
                }
                val progress = if (total > 0) mastered.toFloat() / total else 0f

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = categoryColor.copy(alpha = 0.08f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = category.shortTitle,
                                fontWeight = FontWeight.Bold,
                                color = categoryColor
                            )
                            Text(
                                text = "$mastered / $total naučeno",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = categoryColor,
                            trackColor = categoryColor.copy(alpha = 0.2f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Resetovat progress?", fontWeight = FontWeight.Bold) },
            text = { Text("Tímto smažeš veškerý uložený progress. Tato akce je nevratná.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.resetProgress()
                        showResetDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = IncorrectRed)
                ) {
                    Text("Smazat vše")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Zrušit")
                }
            }
        )
    }
}

@Composable
fun StatBubble(
    value: String,
    label: String,
    color: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(color.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
