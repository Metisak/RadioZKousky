export enum ExamType {
  VFL = 'VFL',
  OFL = 'OFL',
}

export const ExamTypeInfo: Record<ExamType, { title: string; subtitle: string }> = {
  [ExamType.VFL]: {
    title: 'VFL',
    subtitle: 'Všeobecný průkaz radiotelefonisty letecké pohyblivé služby',
  },
  [ExamType.OFL]: {
    title: 'OFL',
    subtitle: 'Omezený průkaz radiotelefonisty letecké pohyblivé služby',
  },
}

export enum Category {
  PREDPISY = 'PREDPISY',
  PROVOZ = 'PROVOZ',
  ELEKTRO = 'ELEKTRO',
}

export const CategoryInfo: Record<Category, { title: string; shortTitle: string }> = {
  [Category.PREDPISY]: { title: 'Radiokomunikační předpisy', shortTitle: 'Předpisy' },
  [Category.PROVOZ]: { title: 'Radiokomunikační provoz', shortTitle: 'Provoz' },
  [Category.ELEKTRO]: { title: 'Elektrotechnika a radiotechnika', shortTitle: 'Elektro' },
}

export interface Question {
  id: number
  category: Category
  text: string
  answer: string
}

export interface QuestionProgress {
  questionId: number
  correctCount: number
  incorrectCount: number
  lastReviewedAt: number
  nextReviewAt: number
  easeFactor: number
  interval: number
  repetition: number
}
