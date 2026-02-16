package com.radiozkousky.data

import kotlin.math.max
import kotlin.math.roundToInt

/**
 * SM-2 (SuperMemo) algorithm for spaced repetition.
 *
 * Quality grades:
 * - correct answer = quality 4 (good)
 * - incorrect answer = quality 1 (bad)
 */
object SpacedRepetition {

    private const val MINUTE_MS = 60_000L
    private const val HOUR_MS = 3_600_000L
    private const val DAY_MS = 86_400_000L

    fun updateProgress(progress: QuestionProgress, correct: Boolean) {
        val quality = if (correct) 4 else 1

        if (quality >= 3) {
            when (progress.repetition) {
                0 -> progress.interval = 1
                1 -> progress.interval = 6
                else -> progress.interval = (progress.interval * progress.easeFactor).roundToInt()
            }
            progress.repetition++
        } else {
            progress.repetition = 0
            progress.interval = 0
        }

        progress.easeFactor = max(
            1.3,
            progress.easeFactor + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02))
        )

        progress.nextReviewAt = if (progress.interval == 0) {
            System.currentTimeMillis() + MINUTE_MS
        } else {
            System.currentTimeMillis() + progress.interval * DAY_MS
        }
    }
}
