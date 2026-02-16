import { Category, ExamType, Question, QuestionProgress } from '../data/types'
import { getAllQuestions, getByCategory } from '../data/questionBank'
import { createProgress, updateProgress } from './spacedRepetition'

const STORAGE_PREFIX = 'radio_zkousky_progress_'

function getStorageKey(examType: ExamType): string {
  return `${STORAGE_PREFIX}${examType}`
}

function loadAll(examType: ExamType): Record<number, QuestionProgress> {
  try {
    const json = localStorage.getItem(getStorageKey(examType))
    if (!json) return {}
    return JSON.parse(json)
  } catch {
    return {}
  }
}

function saveAll(examType: ExamType, data: Record<number, QuestionProgress>): void {
  localStorage.setItem(getStorageKey(examType), JSON.stringify(data))
}

export function getProgress(examType: ExamType, questionId: number): QuestionProgress {
  const all = loadAll(examType)
  return all[questionId] ?? createProgress(questionId)
}

export function recordAnswer(examType: ExamType, questionId: number, correct: boolean): void {
  const all = loadAll(examType)
  const current = all[questionId] ?? createProgress(questionId)
  all[questionId] = updateProgress(current, correct)
  saveAll(examType, all)
}

export function getAllProgress(examType: ExamType): Record<number, QuestionProgress> {
  return loadAll(examType)
}

export function getTotalAnswered(examType: ExamType): number {
  const all = loadAll(examType)
  return Object.values(all).filter((p) => p.correctCount + p.incorrectCount > 0).length
}

export function getTotalCorrect(examType: ExamType): number {
  const all = loadAll(examType)
  return Object.values(all).reduce((sum, p) => sum + p.correctCount, 0)
}

export function getTotalIncorrect(examType: ExamType): number {
  const all = loadAll(examType)
  return Object.values(all).reduce((sum, p) => sum + p.incorrectCount, 0)
}

export function getMasteredCount(examType: ExamType): number {
  const all = loadAll(examType)
  return Object.values(all).filter((p) => p.repetition >= 3).length
}

export function getMasteredCountByCategory(examType: ExamType, category: Category): number {
  const all = loadAll(examType)
  const questionIds = new Set(getByCategory(examType, category).map((q) => q.id))
  return Object.values(all).filter((p) => questionIds.has(p.questionId) && p.repetition >= 3).length
}

export function getQuestionsForReview(examType: ExamType, category?: Category): Question[] {
  const now = Date.now()
  const all = loadAll(examType)
  const questions = category ? getByCategory(examType, category) : getAllQuestions(examType)

  const due = questions.filter((q) => {
    const p = all[q.id]
    return !p || p.nextReviewAt <= now
  })

  if (due.length === 0) {
    return [...questions].sort(() => Math.random() - 0.5).slice(0, 10)
  }

  return due.sort((a, b) => {
    const pa = all[a.id]?.nextReviewAt ?? 0
    const pb = all[b.id]?.nextReviewAt ?? 0
    return pa - pb
  })
}

export function resetAll(examType: ExamType): void {
  localStorage.removeItem(getStorageKey(examType))
}
