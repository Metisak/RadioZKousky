import { useState, useMemo } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { Category, CategoryInfo, ExamType, Question } from '../data/types'
import { getByCategory } from '../data/questionBank'

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

interface CategoryResult {
  correct: number
  total: number
}

export default function Test() {
  const { examType: et } = useParams<{ examType: string }>()
  const examType = et as ExamType
  const navigate = useNavigate()

  const questions = useMemo(() => {
    const p = getByCategory(examType, Category.PREDPISY).sort(() => Math.random() - 0.5)
    const pr = getByCategory(examType, Category.PROVOZ).sort(() => Math.random() - 0.5)
    const e = getByCategory(examType, Category.ELEKTRO).sort(() => Math.random() - 0.5)
    return [...p, ...pr, ...e]
  }, [examType])

  const [index, setIndex] = useState(0)
  const [selectedIdx, setSelectedIdx] = useState<number | null>(null)
  const [answers, setAnswers] = useState<Record<number, boolean>>({})
  const [wrongAnswers, setWrongAnswers] = useState<Array<{ question: Question; selected: string }>>([])
  const [finished, setFinished] = useState(false)
  const [showWrong, setShowWrong] = useState(false)

  const question = questions[index]
  const [options, setOptions] = useState<QuizOption[]>(() => question ? generateOptions(question, examType) : [])

  const progress = questions.length > 0 ? (index / questions.length) * 100 : 0
  const isAnswered = selectedIdx !== null

  const handleSelect = (idx: number) => {
    if (isAnswered) return
    setSelectedIdx(idx)
    const opt = options[idx]
    setAnswers((prev) => ({ ...prev, [question.id]: opt.isCorrect }))
    if (!opt.isCorrect) {
      setWrongAnswers((prev) => [...prev, { question, selected: opt.text }])
    }
  }

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
    const catResults: Record<string, CategoryResult> = {}
    for (const cat of [Category.PREDPISY, Category.PROVOZ, Category.ELEKTRO]) {
      const catQs = questions.filter((q) => q.category === cat)
      const correctCount = catQs.filter((q) => answers[q.id] === true).length
      catResults[cat] = { correct: correctCount, total: catQs.length }
    }

    const passed = Object.values(catResults).every(
      (r) => r.total === 0 || r.correct / r.total >= 0.9
    )

    const catColors: Record<string, string> = {
      [Category.PREDPISY]: '#1565C0',
      [Category.PROVOZ]: '#2E7D32',
      [Category.ELEKTRO]: '#EF6C00',
    }

    if (showWrong) {
      return (
        <div className="flex flex-col min-h-screen">
          <header className="px-4 py-3 flex items-center gap-3 text-white" style={{ backgroundColor: '#EF6C00' }}>
            <button onClick={() => setShowWrong(false)} className="text-2xl">←</button>
            <h1 className="text-lg font-bold">Chybné odpovědi ({wrongAnswers.length})</h1>
          </header>
          <div className="flex-1 p-4 flex flex-col gap-3 overflow-y-auto">
            {wrongAnswers.map((w, i) => (
              <div key={i} className="bg-white dark:bg-gray-800 rounded-xl p-4 shadow-sm border-l-4 border-incorrect">
                <p className="font-medium dark:text-white text-sm">{w.question.text}</p>
                <p className="text-incorrect text-sm mt-2 line-through">{w.selected}</p>
                <p className="text-correct font-bold text-sm mt-1">{w.question.answer}</p>
              </div>
            ))}
          </div>
        </div>
      )
    }

    return (
      <div className="flex flex-col min-h-screen">
        <header className="px-4 py-3 flex items-center gap-3 text-white" style={{ backgroundColor: '#EF6C00' }}>
          <button onClick={() => navigate(-1)} className="text-2xl">←</button>
          <h1 className="text-lg font-bold">Výsledky testu</h1>
        </header>
        <div className="flex-1 p-4">
          <div className="text-center mb-6">
            <div className="text-5xl mb-3">{passed ? '✅' : '❌'}</div>
            <h2 className="text-2xl font-bold dark:text-white">{passed ? 'Splněno!' : 'Nesplněno'}</h2>
            <p className="text-gray-500 dark:text-gray-400 mt-1">
              {passed ? 'Gratulujeme, splnil jsi na 90 % v každém předmětu!' : 'Bohužel, nesplnil jsi limit 90 % v každém předmětu.'}
            </p>
          </div>

          <div className="flex flex-col gap-3">
            {[Category.PREDPISY, Category.PROVOZ, Category.ELEKTRO].map((cat) => {
              const r = catResults[cat]
              const pct = r.total > 0 ? Math.round((r.correct / r.total) * 100) : 0
              const catPassed = pct >= 90
              return (
                <div key={cat} className="bg-white dark:bg-gray-800 rounded-xl p-4 shadow-sm">
                  <div className="flex justify-between items-center mb-2">
                    <span className="font-bold" style={{ color: catColors[cat] }}>
                      {CategoryInfo[cat].shortTitle}
                    </span>
                    <span className={`font-bold ${catPassed ? 'text-correct' : 'text-incorrect'}`}>
                      {pct} %
                    </span>
                  </div>
                  <div className="h-2 rounded-full bg-gray-200 dark:bg-gray-700">
                    <div
                      className="h-full rounded-full transition-all"
                      style={{
                        width: `${pct}%`,
                        backgroundColor: catPassed ? '#4CAF50' : '#F44336',
                      }}
                    />
                  </div>
                  <div className="text-xs text-gray-500 dark:text-gray-400 mt-1">
                    {r.correct} / {r.total} správně
                  </div>
                </div>
              )
            })}
          </div>

          <div className="flex flex-col gap-3 mt-6">
            {wrongAnswers.length > 0 && (
              <button
                onClick={() => setShowWrong(true)}
                className="w-full py-3 bg-incorrect/10 text-incorrect rounded-xl font-medium"
              >
                Zobrazit chybné odpovědi ({wrongAnswers.length})
              </button>
            )}
            <button
              onClick={() => navigate(-1)}
              className="w-full py-3 bg-primary text-white rounded-xl font-bold"
            >
              Zpět
            </button>
          </div>
        </div>
      </div>
    )
  }

  return (
    <div className="flex flex-col min-h-screen">
      <header className="px-4 py-3 flex items-center gap-3 text-white" style={{ backgroundColor: '#EF6C00' }}>
        <button onClick={() => navigate(-1)} className="text-2xl">←</button>
        <h1 className="text-lg font-bold">Simulace testu</h1>
      </header>

      <div className="h-1 bg-gray-200 dark:bg-gray-700">
        <div className="h-full bg-tertiary transition-all duration-300" style={{ width: `${progress}%` }} />
      </div>

      <div className="flex-1 p-4 flex flex-col">
        <div className="flex justify-between text-sm text-gray-500 dark:text-gray-400 mb-3">
          <span>Otázka {index + 1} z {questions.length}</span>
          <span className="px-2 py-0.5 rounded text-xs font-medium"
            style={{
              backgroundColor: question.category === Category.PREDPISY ? '#1565C020' : question.category === Category.PROVOZ ? '#2E7D3220' : '#EF6C0020',
              color: question.category === Category.PREDPISY ? '#1565C0' : question.category === Category.PROVOZ ? '#2E7D32' : '#EF6C00'
            }}
          >
            {CategoryInfo[question.category].shortTitle}
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
            className="mt-4 w-full py-4 bg-tertiary text-white rounded-xl font-bold active:scale-95 transition-transform"
          >
            {index + 1 >= questions.length ? 'Výsledky' : 'Další otázka →'}
          </button>
        )}
      </div>
    </div>
  )
}
