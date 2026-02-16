import { useState, useMemo } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { Category, ExamType } from '../data/types'
import { getShuffled } from '../data/questionBank'
import { recordAnswer, getQuestionsForReview } from '../lib/progressStore'

interface Props {
  mode: 'flashcards' | 'spaced'
}

export default function Flashcards({ mode }: Props) {
  const { examType: et, category: cat } = useParams<{ examType: string; category: string }>()
  const examType = et as ExamType
  const category = cat === 'ALL' ? undefined : (cat as Category)
  const navigate = useNavigate()

  const questions = useMemo(() => {
    if (mode === 'spaced') return getQuestionsForReview(examType, category)
    return getShuffled(examType, category)
  }, [examType, category, mode])

  const [index, setIndex] = useState(0)
  const [showAnswer, setShowAnswer] = useState(false)
  const [finished, setFinished] = useState(false)
  const [knownCount, setKnownCount] = useState(0)

  const question = questions[index]
  const progress = questions.length > 0 ? ((index) / questions.length) * 100 : 0

  const handleKnow = (known: boolean) => {
    if (!question) return
    recordAnswer(examType, question.id, known)
    if (known) setKnownCount((c) => c + 1)
    setShowAnswer(false)
    if (index + 1 >= questions.length) {
      setFinished(true)
    } else {
      setIndex((i) => i + 1)
    }
  }

  if (questions.length === 0) {
    return (
      <div className="flex flex-col min-h-screen">
        <Header title={mode === 'spaced' ? 'Spaced Repetition' : 'Kartičky'} onBack={() => navigate(-1)} />
        <div className="flex-1 flex items-center justify-center p-6">
          <div className="text-center">
            <div className="text-5xl mb-4">✅</div>
            <h2 className="text-xl font-bold dark:text-white">Žádné otázky k opakování</h2>
            <p className="text-gray-500 dark:text-gray-400 mt-2">Všechno máš naučené, vrať se později.</p>
            <button onClick={() => navigate(-1)} className="mt-6 px-6 py-3 bg-primary text-white rounded-xl font-medium">
              Zpět
            </button>
          </div>
        </div>
      </div>
    )
  }

  if (finished) {
    return (
      <div className="flex flex-col min-h-screen">
        <Header title={mode === 'spaced' ? 'Spaced Repetition' : 'Kartičky'} onBack={() => navigate(-1)} />
        <div className="flex-1 flex items-center justify-center p-6">
          <div className="text-center">
            <div className="text-5xl mb-4">🎉</div>
            <h2 className="text-xl font-bold dark:text-white">Hotovo!</h2>
            <p className="text-gray-500 dark:text-gray-400 mt-2">
              Uměl jsi {knownCount} z {questions.length} otázek
            </p>
            <div className="text-3xl font-bold mt-4 dark:text-white">
              {Math.round((knownCount / questions.length) * 100)} %
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
      <Header title={mode === 'spaced' ? 'Spaced Repetition' : 'Kartičky'} onBack={() => navigate(-1)} />

      <div className="h-1 bg-gray-200 dark:bg-gray-700">
        <div className="h-full bg-primary transition-all duration-300" style={{ width: `${progress}%` }} />
      </div>

      <div className="flex-1 p-4 flex flex-col">
        <div className="text-sm text-gray-500 dark:text-gray-400 mb-3">
          Otázka {index + 1} z {questions.length}
        </div>

        <div
          onClick={() => !showAnswer && setShowAnswer(true)}
          className="flex-1 bg-white dark:bg-gray-800 rounded-2xl shadow-md p-6 flex flex-col cursor-pointer"
        >
          <div className="flex-1 flex items-center justify-center">
            <p className="text-lg text-center font-medium dark:text-white">{question.text}</p>
          </div>

          {showAnswer ? (
            <div className="border-t dark:border-gray-700 pt-4 mt-4">
              <div className="text-xs text-gray-400 uppercase tracking-wide mb-2">Odpověď</div>
              <p className="text-lg text-center font-bold text-primary">{question.answer}</p>
            </div>
          ) : (
            <div className="text-center text-gray-400 dark:text-gray-500 text-sm mt-4">
              Klepni pro zobrazení odpovědi
            </div>
          )}
        </div>

        {showAnswer && (
          <div className="flex gap-3 mt-4">
            <button
              onClick={() => handleKnow(false)}
              className="flex-1 py-4 rounded-xl font-bold text-white bg-incorrect active:scale-95 transition-transform"
            >
              Neumím ✕
            </button>
            <button
              onClick={() => handleKnow(true)}
              className="flex-1 py-4 rounded-xl font-bold text-white bg-correct active:scale-95 transition-transform"
            >
              Umím ✓
            </button>
          </div>
        )}
      </div>
    </div>
  )
}

function Header({ title, onBack }: { title: string; onBack: () => void }) {
  return (
    <header className="px-4 py-3 flex items-center gap-3 text-white" style={{ backgroundColor: '#1565C0' }}>
      <button onClick={onBack} className="text-2xl">←</button>
      <h1 className="text-lg font-bold">{title}</h1>
    </header>
  )
}
