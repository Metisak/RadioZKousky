import { Category, CategoryInfo, ExamType } from '../data/types'
import { getByCategory, getAllQuestions } from '../data/questionBank'

interface Props {
  examType: ExamType
  open: boolean
  onClose: () => void
  onSelect: (category: Category | null) => void
}

export default function CategoryDialog({ examType, open, onClose, onSelect }: Props) {
  if (!open) return null

  const categories: { label: string; cat: Category | null; color: string; count: number }[] = [
    { label: 'Všechny kategorie', cat: null, color: 'bg-primary', count: getAllQuestions(examType).length },
    { label: CategoryInfo[Category.PREDPISY].shortTitle, cat: Category.PREDPISY, color: 'bg-predpisy', count: getByCategory(examType, Category.PREDPISY).length },
    { label: CategoryInfo[Category.PROVOZ].shortTitle, cat: Category.PROVOZ, color: 'bg-provoz', count: getByCategory(examType, Category.PROVOZ).length },
    { label: CategoryInfo[Category.ELEKTRO].shortTitle, cat: Category.ELEKTRO, color: 'bg-elektro', count: getByCategory(examType, Category.ELEKTRO).length },
  ]

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 p-4" onClick={onClose}>
      <div
        className="bg-white dark:bg-gray-800 rounded-2xl w-full max-w-sm p-5 shadow-xl"
        onClick={(e) => e.stopPropagation()}
      >
        <h3 className="text-lg font-bold mb-4 dark:text-white">Vyber kategorii</h3>
        <div className="flex flex-col gap-2">
          {categories.map((c) => (
            <button
              key={c.label}
              onClick={() => onSelect(c.cat)}
              className="flex items-center justify-between rounded-xl p-4 transition-colors hover:bg-gray-100 dark:hover:bg-gray-700"
            >
              <div className="flex items-center gap-3">
                <div className={`w-3 h-3 rounded-full ${c.color}`} />
                <span className="font-medium dark:text-white">{c.label}</span>
              </div>
              <span className="text-sm text-gray-500 dark:text-gray-400">{c.count} otázek</span>
            </button>
          ))}
        </div>
        <button
          onClick={onClose}
          className="mt-4 w-full text-center text-primary font-medium py-2"
        >
          Zrušit
        </button>
      </div>
    </div>
  )
}
