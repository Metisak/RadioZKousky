import { useState, useMemo, useCallback } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { Category, ExamType, Question } from '../data/types'
import { getShuffled, getByCategory } from '../data/questionBank'
import { recordAnswer } from '../lib/progressStore'

interface QuizOption {
  text: string
  isCorrect: boolean
}

function generateOptions(question: Question, examType: ExamType): QuizOption[] {
  const sameCategory = getByCategory(examType, question.category).filter((q) => q.id !== question.id)
  const shuffled = [...sameCategory].sort(() => Math.random() - 0.5)
  const wrongAnswers = [...new Set(shuffled.map((q) => q.answer))]
    .filter((a) => a !== question.answer)
    .slice(0, 3)

  const options: QuizOption[] = [
    ...wrongAnswers.map((text) => ({ text, isCorrect: false })),
    { text: question.answer, isCorrect: true },
  ]
  return options.sort(() => Math.random() - 0.5)
}

export default function Quiz() {
  const { examType: et, category: cat } = useParams<{ examType: string; category: string }>()
  const examType = et as ExamType
  const category = cat === 'ALL' ? undefined : (cat as Category)
  const navigate = useNavigate()

  const questions = useMemo(() => getShuffled(examType, category), [examType, category])

  const [index, setIndex] = useState(0)
  const [selectedIdx, setSelectedIdx] = useState<number | null>(null)
  const [correct, setCorrect] = useState(0)
  const [incorrect, setIncorrect] = useState(0)
  const [finished, setFinished] = useState(false)

  const question = questions[index]
  const [options, setOptions] = useState<QuizOption[]>(() => question ? generateOptions(question, examType) : [])

  const progress = questions.length > 0 ? (index / questions.length) * 100 : 0
  const isAnswered = selectedIdx !== null

  const handleSelect = useCallback((idx: number) => {
    if (isAnswered) return
    setSelectedIdx(idx)
    const opt = options[idx]
    recordAnswer(examType, question.id, opt.isCorrect)
    if (opt.isCorrect) setCorrect((c) => c + 1)
    else setIncorrect((c) => c + 1)
  }, [isAnswered, options, examType, question])

  const handleNext = () => {
    if (index + 1 >= questions.length) {
      setFinished(true)
    } else {
      const nextQ = questions[index + 1]
      setIndex((i) => i + 1)
      setSelectedIdx(null)
      setOptions(generateOptions(nextQ, examType))
    }
  }

  if (finished) {
    const total = correct + incorrect
    const pct = total > 0 ? Math.round((correct / total) * 100) : 0
    return (
      <div className="flex flex-col min-h-screen">
        <Header onBack={() => navigate(-1)} />
        <div className="flex-1 flex items-center justify-center p-6">
          <div className="text-center">
            <div className="text-5xl mb-4">{pct >= 90 ? '🏆' : pct >= 70 ? '👍' : '📚'}</div>
            <h2 className="text-xl font-bold dark:text-white">Kvíz dokončen!</h2>
            <div className="text-4xl font-bold mt-4 dark:text-white">{pct} %</div>
            <div className="flex justify-center gap-8 mt-4">
              <div className="text-center">
                <div className="text-2xl font-bold text-correct">{correct}</div>
                <div className="text-xs text-gray-500">Správně</div>
              </div>
              <div className="text-center">
                <div className="text-2xl font-bold text-incorrect">{incorrect}</div>
                <div className="text-xs text-gray-500">Špatně</div>
              </div>
            </div>
            <button onClick={() => navigate(-1)} className="mt-6 px-6 py-3 bg-primary text-white rounded-xl font-medium">
              Zpět
            </button>
          </div>
        </div>
      </div>
    )
  }

  return (
    <div className="flex flex-col min-h-screen">
      <Header onBack={() => navigate(-1)} />

      <div className="h-1 bg-gray-200 dark:bg-gray-700">
        <div className="h-full bg-primary transition-all duration-300" style={{ width: `${progress}%` }} />
      </div>

      <div className="flex-1 p-4 flex flex-col">
        <div className="flex justify-between text-sm text-gray-500 dark:text-gray-400 mb-3">
          <span>Otázka {index + 1} z {questions.length}</span>
          <span className="font-medium">
            <span className="text-correct">{correct}</span> / <span className="text-incorrect">{incorrect}</span>
          </span>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-2xl shadow-md p-6 mb-4">
          <p className="text-lg font-medium dark:text-white text-center">{question.text}</p>
        </div>

        <div className="flex flex-col gap-3 flex-1">
          {options.map((opt, idx) => {
            let cls = 'border-2 border-gray-200 dark:border-gray-700 bg-white dark:bg-gray-800'
            if (isAnswered) {
              if (opt.isCorrect) cls = 'border-2 border-correct bg-correct/10'
              else if (idx === selectedIdx) cls = 'border-2 border-incorrect bg-incorrect/10'
            }
            return (
              <button
                key={idx}
                onClick={() => handleSelect(idx)}
                className={`w-full rounded-xl p-4 text-left transition-all active:scale-[0.98] ${cls}`}
              >
                <span className={`text-sm ${isAnswered && opt.isCorrect ? 'font-bold text-correct' : isAnswered && idx === selectedIdx && !opt.isCorrect ? 'text-incorrect' : 'dark:text-white'}`}>
                  {opt.text}
                </span>
              </button>
            )
          })}
        </div>

        {isAnswered && (
          <button
            onClick={handleNext}
            className="mt-4 w-full py-4 bg-primary text-white rounded-xl font-bold active:scale-95 transition-transform"
          >
            {index + 1 >= questions.length ? 'Výsledky' : 'Další otázka →'}
          </button>
        )}
      </div>
    </div>
  )
}

function Header({ onBack }: { onBack: () => void }) {
  return (
    <header className="px-4 py-3 flex items-center gap-3 text-white" style={{ backgroundColor: '#2E7D32' }}>
      <button onClick={onBack} className="text-2xl">←</button>
      <h1 className="text-lg font-bold">Kvíz</h1>
    </header>
  )
}
