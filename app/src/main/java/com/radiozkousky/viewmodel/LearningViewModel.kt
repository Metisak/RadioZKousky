package com.radiozkousky.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.radiozkousky.data.Category
import com.radiozkousky.data.ProgressStore
import com.radiozkousky.data.Question
import com.radiozkousky.data.QuestionBank
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuizOption(
    val text: String,
    val isCorrect: Boolean
)

data class FlashcardState(
    val questions: List<Question> = emptyList(),
    val currentIndex: Int = 0,
    val isAnswerVisible: Boolean = false,
    val isFinished: Boolean = false
) {
    val currentQuestion: Question? get() = questions.getOrNull(currentIndex)
    val progress: Float get() = if (questions.isEmpty()) 0f else (currentIndex.toFloat() / questions.size)
}

data class QuizState(
    val questions: List<Question> = emptyList(),
    val currentIndex: Int = 0,
    val options: List<QuizOption> = emptyList(),
    val selectedOptionIndex: Int? = null,
    val isAnswered: Boolean = false,
    val correctCount: Int = 0,
    val incorrectCount: Int = 0,
    val isFinished: Boolean = false
) {
    val currentQuestion: Question? get() = questions.getOrNull(currentIndex)
    val progress: Float get() = if (questions.isEmpty()) 0f else (currentIndex.toFloat() / questions.size)
    val totalAnswered: Int get() = correctCount + incorrectCount
}

data class TestResult(
    val categoryResults: Map<Category, Pair<Int, Int>>,
    val passed: Boolean,
    val wrongAnswers: List<Pair<Question, String>>
)

data class TestState(
    val questions: List<Question> = emptyList(),
    val currentIndex: Int = 0,
    val options: List<QuizOption> = emptyList(),
    val selectedOptionIndex: Int? = null,
    val isAnswered: Boolean = false,
    val answers: MutableMap<Int, Boolean> = mutableMapOf(),
    val wrongAnswers: MutableList<Pair<Question, String>> = mutableListOf(),
    val isFinished: Boolean = false,
    val result: TestResult? = null
) {
    val currentQuestion: Question? get() = questions.getOrNull(currentIndex)
    val progress: Float get() = if (questions.isEmpty()) 0f else (currentIndex.toFloat() / questions.size)
}

data class StatsState(
    val totalQuestions: Int = 164,
    val totalAnswered: Int = 0,
    val totalCorrect: Int = 0,
    val totalIncorrect: Int = 0,
    val masteredCount: Int = 0,
    val categoryStats: Map<Category, Pair<Int, Int>> = emptyMap()
)

class LearningViewModel(application: Application) : AndroidViewModel(application) {

    val progressStore = ProgressStore(application)

    private val _flashcardState = MutableStateFlow(FlashcardState())
    val flashcardState: StateFlow<FlashcardState> = _flashcardState.asStateFlow()

    private val _quizState = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

    private val _testState = MutableStateFlow(TestState())
    val testState: StateFlow<TestState> = _testState.asStateFlow()

    private val _statsState = MutableStateFlow(StatsState())
    val statsState: StateFlow<StatsState> = _statsState.asStateFlow()

    // ==================== FLASHCARDS ====================

    fun startFlashcards(category: Category?) {
        val questions = QuestionBank.getShuffled(category)
        _flashcardState.value = FlashcardState(questions = questions)
    }

    fun toggleAnswer() {
        _flashcardState.value = _flashcardState.value.copy(
            isAnswerVisible = !_flashcardState.value.isAnswerVisible
        )
    }

    fun flashcardKnown() {
        val state = _flashcardState.value
        val question = state.currentQuestion ?: return
        progressStore.recordAnswer(question.id, true)
        moveToNextFlashcard()
    }

    fun flashcardUnknown() {
        val state = _flashcardState.value
        val question = state.currentQuestion ?: return
        progressStore.recordAnswer(question.id, false)
        moveToNextFlashcard()
    }

    private fun moveToNextFlashcard() {
        val state = _flashcardState.value
        val nextIndex = state.currentIndex + 1
        if (nextIndex >= state.questions.size) {
            _flashcardState.value = state.copy(isFinished = true, isAnswerVisible = false)
        } else {
            _flashcardState.value = state.copy(
                currentIndex = nextIndex,
                isAnswerVisible = false
            )
        }
    }

    // ==================== QUIZ ====================

    fun startQuiz(category: Category?) {
        val questions = QuestionBank.getShuffled(category)
        _quizState.value = QuizState(questions = questions)
        generateQuizOptions()
    }

    private fun generateQuizOptions() {
        val state = _quizState.value
        val question = state.currentQuestion ?: return
        val sameCategory = QuestionBank.getByCategory(question.category)
            .filter { it.id != question.id }
            .shuffled()

        val wrongAnswers = sameCategory
            .map { it.answer }
            .distinct()
            .filter { it != question.answer }
            .take(3)

        val allOptions = (wrongAnswers.map { QuizOption(it, false) } +
                QuizOption(question.answer, true)).shuffled()

        _quizState.value = state.copy(options = allOptions)
    }

    fun selectQuizOption(index: Int) {
        val state = _quizState.value
        if (state.isAnswered) return

        val option = state.options.getOrNull(index) ?: return
        val question = state.currentQuestion ?: return

        progressStore.recordAnswer(question.id, option.isCorrect)

        _quizState.value = state.copy(
            selectedOptionIndex = index,
            isAnswered = true,
            correctCount = if (option.isCorrect) state.correctCount + 1 else state.correctCount,
            incorrectCount = if (!option.isCorrect) state.incorrectCount + 1 else state.incorrectCount
        )
    }

    fun nextQuizQuestion() {
        val state = _quizState.value
        val nextIndex = state.currentIndex + 1
        if (nextIndex >= state.questions.size) {
            _quizState.value = state.copy(isFinished = true)
        } else {
            _quizState.value = state.copy(
                currentIndex = nextIndex,
                selectedOptionIndex = null,
                isAnswered = false
            )
            generateQuizOptions()
        }
    }

    // ==================== TEST ====================

    fun startTest() {
        val predpisy = QuestionBank.getByCategory(Category.PREDPISY).shuffled()
        val provoz = QuestionBank.getByCategory(Category.PROVOZ).shuffled()
        val elektro = QuestionBank.getByCategory(Category.ELEKTRO).shuffled()

        val questions = predpisy + provoz + elektro

        _testState.value = TestState(
            questions = questions,
            answers = mutableMapOf(),
            wrongAnswers = mutableListOf()
        )
        generateTestOptions()
    }

    private fun generateTestOptions() {
        val state = _testState.value
        val question = state.currentQuestion ?: return
        val sameCategory = QuestionBank.getByCategory(question.category)
            .filter { it.id != question.id }
            .shuffled()

        val wrongAnswers = sameCategory
            .map { it.answer }
            .distinct()
            .filter { it != question.answer }
            .take(3)

        val allOptions = (wrongAnswers.map { QuizOption(it, false) } +
                QuizOption(question.answer, true)).shuffled()

        _testState.value = state.copy(options = allOptions)
    }

    fun selectTestOption(index: Int) {
        val state = _testState.value
        if (state.isAnswered) return

        val option = state.options.getOrNull(index) ?: return
        val question = state.currentQuestion ?: return

        state.answers[question.id] = option.isCorrect
        if (!option.isCorrect) {
            state.wrongAnswers.add(Pair(question, option.text))
        }

        _testState.value = state.copy(
            selectedOptionIndex = index,
            isAnswered = true
        )
    }

    fun nextTestQuestion() {
        val state = _testState.value
        val nextIndex = state.currentIndex + 1
        if (nextIndex >= state.questions.size) {
            finishTest()
        } else {
            _testState.value = state.copy(
                currentIndex = nextIndex,
                selectedOptionIndex = null,
                isAnswered = false
            )
            generateTestOptions()
        }
    }

    private fun finishTest() {
        val state = _testState.value
        val categoryResults = mutableMapOf<Category, Pair<Int, Int>>()

        for (category in Category.entries) {
            val categoryQuestions = state.questions.filter { it.category == category }
            val correct = categoryQuestions.count { state.answers[it.id] == true }
            categoryResults[category] = Pair(correct, categoryQuestions.size)
        }

        val passed = categoryResults.all { (_, result) ->
            val (correct, total) = result
            total == 0 || (correct.toDouble() / total >= 0.9)
        }

        val result = TestResult(
            categoryResults = categoryResults,
            passed = passed,
            wrongAnswers = state.wrongAnswers.toList()
        )

        _testState.value = state.copy(isFinished = true, result = result)
    }

    // ==================== SPACED REPETITION ====================

    fun startSpacedRepetition(category: Category?) {
        val questions = progressStore.getQuestionsForReview(category)
        _flashcardState.value = FlashcardState(questions = questions)
    }

    // ==================== STATS ====================

    fun refreshStats() {
        val categoryStats = Category.entries.associateWith { category ->
            val total = QuestionBank.getByCategory(category).size
            val mastered = progressStore.getMasteredCountByCategory(category)
            Pair(mastered, total)
        }

        _statsState.value = StatsState(
            totalQuestions = QuestionBank.allQuestions.size,
            totalAnswered = progressStore.getTotalAnswered(),
            totalCorrect = progressStore.getTotalCorrect(),
            totalIncorrect = progressStore.getTotalIncorrect(),
            masteredCount = progressStore.getMasteredCount(),
            categoryStats = categoryStats
        )
    }

    fun resetProgress() {
        progressStore.resetAll()
        refreshStats()
    }
}
