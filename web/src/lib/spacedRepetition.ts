import type { QuestionProgress } from '../data/types'

const MINUTE_MS = 60_000
const DAY_MS = 86_400_000

export function updateProgress(progress: QuestionProgress, correct: boolean): QuestionProgress {
  const quality = correct ? 4 : 1
  const p = { ...progress }

  if (quality >= 3) {
    switch (p.repetition) {
      case 0:
        p.interval = 1
        break
      case 1:
        p.interval = 6
        break
      default:
        p.interval = Math.round(p.interval * p.easeFactor)
        break
    }
    p.repetition++
  } else {
    p.repetition = 0
    p.interval = 0
  }

  p.easeFactor = Math.max(
    1.3,
    p.easeFactor + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02))
  )

  if (correct) {
    p.correctCount++
  } else {
    p.incorrectCount++
  }

  p.lastReviewedAt = Date.now()
  p.nextReviewAt = p.interval === 0 ? Date.now() + MINUTE_MS : Date.now() + p.interval * DAY_MS

  return p
}

export function createProgress(questionId: number): QuestionProgress {
  return {
    questionId,
    correctCount: 0,
    incorrectCount: 0,
    lastReviewedAt: 0,
    nextReviewAt: 0,
    easeFactor: 2.5,
    interval: 0,
    repetition: 0,
  }
}
