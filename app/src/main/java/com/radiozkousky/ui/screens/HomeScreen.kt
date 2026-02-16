package com.radiozkousky.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radiozkousky.data.Category
import com.radiozkousky.data.QuestionBank
import com.radiozkousky.ui.theme.*
import com.radiozkousky.viewmodel.LearningViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: LearningViewModel,
    onNavigateToFlashcards: (Category?) -> Unit,
    onNavigateToQuiz: (Category?) -> Unit,
    onNavigateToTest: () -> Unit,
    onNavigateToSpaced: (Category?) -> Unit,
    onNavigateToStats: () -> Unit
) {
    var showCategoryDialog by remember { mutableStateOf(false) }
    var pendingMode by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Radio Zkoušky VFL",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = onNavigateToStats) {
                        Icon(Icons.Default.BarChart, contentDescription = "Statistiky")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
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
            Text(
                text = "Připrav se na zkoušky",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "${QuestionBank.allQuestions.size} otázek ve 3 kategoriích • splnění na 90 %",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            ModeCard(
                title = "Kartičky",
                description = "Procházej otázky a odhaluj odpovědi. Označ si, co umíš a co ne.",
                icon = Icons.Default.Style,
                gradientColors = listOf(
                    CategoryPredpisyColor,
                    CategoryPredpisyColor.copy(alpha = 0.7f)
                ),
                onClick = {
                    pendingMode = "flashcards"
                    showCategoryDialog = true
                }
            )

            ModeCard(
                title = "Kvíz",
                description = "Vyber správnou odpověď ze 4 možností. Okamžitá zpětná vazba.",
                icon = Icons.Default.Quiz,
                gradientColors = listOf(
                    CategoryProvozColor,
                    CategoryProvozColor.copy(alpha = 0.7f)
                ),
                onClick = {
                    pendingMode = "quiz"
                    showCategoryDialog = true
                }
            )

            ModeCard(
                title = "Simulace testu",
                description = "Zkus si nanečisto kompletní test. Vyhodnocení na 90 % v každém předmětu.",
                icon = Icons.AutoMirrored.Filled.Assignment,
                gradientColors = listOf(
                    CategoryElektroColor,
                    CategoryElektroColor.copy(alpha = 0.7f)
                ),
                onClick = onNavigateToTest
            )

            ModeCard(
                title = "Spaced Repetition",
                description = "Chytré opakování. Obtížnější otázky se ukazují častěji.",
                icon = Icons.Default.Psychology,
                gradientColors = listOf(
                    Color(0xFF7B1FA2),
                    Color(0xFF7B1FA2).copy(alpha = 0.7f)
                ),
                onClick = {
                    pendingMode = "spaced"
                    showCategoryDialog = true
                }
            )
        }
    }

    if (showCategoryDialog) {
        CategorySelectionDialog(
            onDismiss = { showCategoryDialog = false },
            onCategorySelected = { category ->
                showCategoryDialog = false
                when (pendingMode) {
                    "flashcards" -> onNavigateToFlashcards(category)
                    "quiz" -> onNavigateToQuiz(category)
                    "spaced" -> onNavigateToSpaced(category)
                }
            }
        )
    }
}

@Composable
fun ModeCard(
    title: String,
    description: String,
    icon: ImageVector,
    gradientColors: List<Color>,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brush.horizontalGradient(gradientColors))
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 14.sp
                    )
                }
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun CategorySelectionDialog(
    onDismiss: () -> Unit,
    onCategorySelected: (Category?) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                "Vyber kategorii",
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                CategoryDialogItem(
                    title = "Všechny kategorie",
                    count = QuestionBank.allQuestions.size,
                    color = MaterialTheme.colorScheme.primary,
                    onClick = { onCategorySelected(null) }
                )
                CategoryDialogItem(
                    title = Category.PREDPISY.shortTitle,
                    count = QuestionBank.getByCategory(Category.PREDPISY).size,
                    color = CategoryPredpisyColor,
                    onClick = { onCategorySelected(Category.PREDPISY) }
                )
                CategoryDialogItem(
                    title = Category.PROVOZ.shortTitle,
                    count = QuestionBank.getByCategory(Category.PROVOZ).size,
                    color = CategoryProvozColor,
                    onClick = { onCategorySelected(Category.PROVOZ) }
                )
                CategoryDialogItem(
                    title = Category.ELEKTRO.shortTitle,
                    count = QuestionBank.getByCategory(Category.ELEKTRO).size,
                    color = CategoryElektroColor,
                    onClick = { onCategorySelected(Category.ELEKTRO) }
                )
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Zrušit")
            }
        }
    )
}

@Composable
fun CategoryDialogItem(
    title: String,
    count: Int,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(color)
                )
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
            Text(
                text = "$count otázek",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
        }
    }
}
