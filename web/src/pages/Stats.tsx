import { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { Category, CategoryInfo, ExamType } from '../data/types'
import { getAllQuestions, getByCategory } from '../data/questionBank'
import * as store from '../lib/progressStore'

interface StatsData {
  totalQuestions: number
  totalAnswered: number
  totalCorrect: number
  totalIncorrect: number
  masteredCount: number
  categoryStats: Record<Category, { mastered: number; total: number }>
}

export default function Stats() {
  const { examType: et } = useParams<{ examType: string }>()
  const examType = et as ExamType
  const navigate = useNavigate()
  const [showReset, setShowReset] = useState(false)
  const [stats, setStats] = useState<StatsData | null>(null)

  const refresh = () => {
    const catStats = {} as Record<Category, { mastered: number; total: number }>
    for (const cat of [Category.PREDPISY, Category.PROVOZ, Category.ELEKTRO]) {
      catStats[cat] = {
        mastered: store.getMasteredCountByCategory(examType, cat),
        total: getByCategory(examType, cat).length,
      }
    }
    setStats({
      totalQuestions: getAllQuestions(examType).length,
      totalAnswered: store.getTotalAnswered(examType),
      totalCorrect: store.getTotalCorrect(examType),
      totalIncorrect: store.getTotalIncorrect(examType),
      masteredCount: store.getMasteredCount(examType),
      categoryStats: catStats,
    })
  }

  useEffect(refresh, [examType])

  const handleReset = () => {
    store.resetAll(examType)
    refresh()
    setShowReset(false)
  }

  if (!stats) return null

  const masteredPct = stats.totalQuestions > 0 ? (stats.masteredCount / stats.totalQuestions) * 100 : 0
  const totalAttempts = stats.totalCorrect + stats.totalIncorrect
  const accuracy = totalAttempts > 0 ? Math.round((stats.totalCorrect / totalAttempts) * 100) : 0

  const catColors: Record<Category, string> = {
    [Category.PREDPISY]: '#1565C0',
    [Category.PROVOZ]: '#2E7D32',
    [Category.ELEKTRO]: '#EF6C00',
  }

  return (
    <div className="flex flex-col min-h-screen">
      <header className="px-4 py-3 flex items-center gap-3 text-white" style={{ backgroundColor: '#1565C0' }}>
        <button onClick={() => navigate(-1)} className="text-2xl">←</button>
        <h1 className="text-lg font-bold flex-1">Statistiky {examType}</h1>
        <button onClick={() => setShowReset(true)} className="text-xl">🗑</button>
      </header>

      <div className="flex-1 p-4 flex flex-col gap-4 overflow-y-auto">
        <div className="bg-white dark:bg-gray-800 rounded-2xl shadow-md p-5">
          <h3 className="text-lg font-bold text-center dark:text-white mb-4">Celkový přehled</h3>
          <div className="flex justify-around mb-4">
            <Bubble value={stats.totalAnswered} label="Zodpovězeno" color="#1565C0" />
            <Bubble value={stats.masteredCount} label="Naučeno" color="#4CAF50" />
            <Bubble value={stats.totalQuestions} label="Celkem" color="#EF6C00" />
          </div>
          <div className="h-3 rounded-full bg-gray-200 dark:bg-gray-700">
            <div className="h-full rounded-full bg-correct transition-all" style={{ width: `${masteredPct}%` }} />
          </div>
          <p className="text-center text-sm text-gray-500 dark:text-gray-400 mt-2">
            {Math.round(masteredPct)} % naučeno
          </p>
        </div>

        {totalAttempts > 0 && (
          <div className="bg-white dark:bg-gray-800 rounded-2xl shadow-md p-5">
            <h3 className="font-bold dark:text-white mb-3">Přesnost odpovědí</h3>
            <div className="flex justify-around">
              <div className="text-center">
                <div className={`text-2xl font-bold ${accuracy >= 90 ? 'text-correct' : accuracy >= 70 ? 'text-tertiary' : 'text-incorrect'}`}>
                  {accuracy} %
                </div>
                <div className="text-xs text-gray-500">Úspěšnost</div>
              </div>
              <div className="text-center">
                <div className="text-2xl font-bold text-correct">{stats.totalCorrect}</div>
                <div className="text-xs text-gray-500">Správně</div>
              </div>
              <div className="text-center">
                <div className="text-2xl font-bold text-incorrect">{stats.totalIncorrect}</div>
                <div className="text-xs text-gray-500">Špatně</div>
              </div>
            </div>
          </div>
        )}

        <h3 className="font-bold dark:text-white mt-2">Progress podle kategorie</h3>

        {[Category.PREDPISY, Category.PROVOZ, Category.ELEKTRO].map((cat) => {
          const s = stats.categoryStats[cat]
          const pct = s.total > 0 ? (s.mastered / s.total) * 100 : 0
          return (
            <div key={cat} className="bg-white dark:bg-gray-800 rounded-xl p-4 shadow-sm">
              <div className="flex justify-between items-center mb-2">
                <span className="font-bold" style={{ color: catColors[cat] }}>
                  {CategoryInfo[cat].shortTitle}
                </span>
                <span className="text-sm text-gray-500 dark:text-gray-400">{s.mastered} / {s.total} naučeno</span>
              </div>
              <div className="h-2 rounded-full bg-gray-200 dark:bg-gray-700">
                <div
                  className="h-full rounded-full transition-all"
                  style={{ width: `${pct}%`, backgroundColor: catColors[cat] }}
                />
              </div>
            </div>
          )
        })}
      </div>

      {showReset && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 p-4" onClick={() => setShowReset(false)}>
          <div className="bg-white dark:bg-gray-800 rounded-2xl p-5 w-full max-w-sm shadow-xl" onClick={(e) => e.stopPropagation()}>
            <h3 className="text-lg font-bold dark:text-white">Resetovat progress?</h3>
            <p className="text-gray-500 dark:text-gray-400 text-sm mt-2">
              Tímto smažeš veškerý uložený progress pro {examType}. Tato akce je nevratná.
            </p>
            <div className="flex gap-3 mt-4">
              <button onClick={() => setShowReset(false)} className="flex-1 py-2 rounded-xl border dark:border-gray-700 font-medium dark:text-white">
                Zrušit
              </button>
              <button onClick={handleReset} className="flex-1 py-2 rounded-xl bg-incorrect text-white font-medium">
                Smazat vše
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  )
}

function Bubble({ value, label, color }: { value: number; label: string; color: string }) {
  return (
    <div className="text-center">
      <div
        className="w-16 h-16 rounded-full flex items-center justify-center mx-auto"
        style={{ backgroundColor: `${color}20` }}
      >
        <span className="text-xl font-bold" style={{ color }}>{value}</span>
      </div>
      <div className="text-xs text-gray-500 dark:text-gray-400 mt-1">{label}</div>
    </div>
  )
}
