import { Category, ExamType, Question } from './types'
import { questionsVFL } from './questionsVFL'
import { questionsOFL } from './questionsOFL'

function shuffleArray<T>(array: T[]): T[] {
  const shuffled = [...array]
  for (let i = shuffled.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1))
    ;[shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]]
  }
  return shuffled
}

export function getAllQuestions(examType: ExamType): Question[] {
  return examType === ExamType.VFL ? questionsVFL : questionsOFL
}

export function getByCategory(examType: ExamType, category: Category): Question[] {
  return getAllQuestions(examType).filter((q) => q.category === category)
}

export function getShuffled(examType: ExamType, category?: Category): Question[] {
  const questions = category ? getByCategory(examType, category) : getAllQuestions(examType)
  return shuffleArray(questions)
}
