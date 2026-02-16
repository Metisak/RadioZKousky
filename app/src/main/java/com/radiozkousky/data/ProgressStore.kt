package com.radiozkousky.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class QuestionProgress(
    val questionId: Int = 0,
    var correctCount: Int = 0,
    var incorrectCount: Int = 0,
    var lastReviewedAt: Long = 0L,
    var nextReviewAt: Long = 0L,
    var easeFactor: Double = 2.5,
    var interval: Int = 0,
    var repetition: Int = 0
)

class ProgressStore(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("radio_zkousky_progress", Context.MODE_PRIVATE)
    private val gson = Gson()

    private var progressMap: MutableMap<Int, QuestionProgress> = loadAll()

    private fun loadAll(): MutableMap<Int, QuestionProgress> {
        val json = prefs.getString("progress_data", null) ?: return mutableMapOf()
        val type = object : TypeToken<MutableMap<Int, QuestionProgress>>() {}.type
        return try {
            gson.fromJson(json, type) ?: mutableMapOf()
        } catch (e: Exception) {
            mutableMapOf()
        }
    }

    private fun saveAll() {
        val json = gson.toJson(progressMap)
        prefs.edit().putString("progress_data", json).apply()
    }

    fun getProgress(questionId: Int): QuestionProgress {
        return progressMap.getOrPut(questionId) { QuestionProgress(questionId = questionId) }
    }

    fun recordAnswer(questionId: Int, correct: Boolean) {
        val progress = getProgress(questionId)
        if (correct) {
            progress.correctCount++
        } else {
            progress.incorrectCount++
        }
        progress.lastReviewedAt = System.currentTimeMillis()
        SpacedRepetition.updateProgress(progress, correct)
        progressMap[questionId] = progress
        saveAll()
    }

    fun getAllProgress(): Map<Int, QuestionProgress> = progressMap.toMap()

    fun getCorrectCount(questionId: Int): Int = getProgress(questionId).correctCount

    fun getTotalAnswered(): Int = progressMap.values.count { it.correctCount + it.incorrectCount > 0 }

    fun getTotalCorrect(): Int = progressMap.values.sumOf { it.correctCount }

    fun getTotalIncorrect(): Int = progressMap.values.sumOf { it.incorrectCount }

    fun getMasteredCount(): Int = progressMap.values.count { it.repetition >= 3 }

    fun getMasteredCountByCategory(category: Category): Int {
        val questionIds = QuestionBank.getByCategory(category).map { it.id }.toSet()
        return progressMap.values.count { it.questionId in questionIds && it.repetition >= 3 }
    }

    fun getQuestionsForReview(category: Category? = null): List<Question> {
        val now = System.currentTimeMillis()
        val questions = if (category != null) {
            QuestionBank.getByCategory(category)
        } else {
            QuestionBank.allQuestions
        }

        val dueQuestions = questions.filter { q ->
            val progress = progressMap[q.id]
            progress == null || progress.nextReviewAt <= now
        }

        return if (dueQuestions.isEmpty()) {
            questions.shuffled().take(10)
        } else {
            dueQuestions.sortedBy { q ->
                progressMap[q.id]?.nextReviewAt ?: 0L
            }
        }
    }

    fun resetAll() {
        progressMap.clear()
        prefs.edit().clear().apply()
    }
}
