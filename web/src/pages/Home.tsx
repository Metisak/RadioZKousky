import { useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { Category, ExamType } from '../data/types'
import { getAllQuestions } from '../data/questionBank'
import CategoryDialog from '../components/CategoryDialog'

const modes = [
  {
    id: 'flashcards',
    title: 'Kartičky',
    desc: 'Procházej otázky a odhaluj odpovědi. Označ si, co umíš a co ne.',
    icon: '🃏',
    gradient: 'linear-gradient(135deg, #1565C0, #1565C0cc)',
    hasCategory: true,
  },
  {
    id: 'quiz',
    title: 'Kvíz',
    desc: 'Vyber správnou odpověď ze 4 možností. Okamžitá zpětná vazba.',
    icon: '❓',
    gradient: 'linear-gradient(135deg, #2E7D32, #2E7D32cc)',
    hasCategory: true,
  },
  {
    id: 'test',
    title: 'Simulace testu',
    desc: 'Zkus si nanečisto kompletní test. Vyhodnocení na 90 % v každém předmětu.',
    icon: '📝',
    gradient: 'linear-gradient(135deg, #EF6C00, #EF6C00cc)',
    hasCategory: false,
  },
  {
    id: 'spaced',
    title: 'Spaced Repetition',
    desc: 'Chytré opakování. Obtížnější otázky se ukazují častěji.',
    icon: '🧠',
    gradient: 'linear-gradient(135deg, #7B1FA2, #7B1FA2cc)',
    hasCategory: true,
  },
]

export default function Home() {
  const { examType: et } = useParams<{ examType: string }>()
  const examType = et as ExamType
  const navigate = useNavigate()
  const [dialogOpen, setDialogOpen] = useState(false)
  const [pendingMode, setPendingMode] = useState('')
  const total = getAllQuestions(examType).length

  const handleModeClick = (mode: typeof modes[0]) => {
    if (mode.hasCategory) {
      setPendingMode(mode.id)
      setDialogOpen(true)
    } else {
      navigate(`/test/${examType}`)
    }
  }

  const handleCategorySelect = (category: Category | null) => {
    setDialogOpen(false)
    const cat = category?.toString() ?? 'ALL'
    navigate(`/${pendingMode}/${examType}/${cat}`)
  }

  return (
    <div className="flex flex-col min-h-screen">
      <header
        className="px-4 py-3 flex items-center gap-3 text-white"
        style={{ backgroundColor: '#1565C0' }}
      >
        <button onClick={() => navigate('/')} className="text-2xl">←</button>
        <h1 className="text-lg font-bold flex-1">Radio Zkoušky {examType}</h1>
        <button onClick={() => navigate(`/stats/${examType}`)} className="text-xl">📊</button>
      </header>

      <div className="flex-1 p-4 flex flex-col gap-4">
        <div>
          <h2 className="text-xl font-bold dark:text-white">Připrav se na zkoušky</h2>
          <p className="text-sm text-gray-500 dark:text-gray-400 mt-1">
            {total} otázek ve 3 kategoriích &bull; splnění na 90 %
          </p>
        </div>

        {modes.map((mode) => (
          <button
            key={mode.id}
            onClick={() => handleModeClick(mode)}
            className="w-full rounded-2xl p-5 text-left text-white shadow-md active:scale-[0.98] transition-transform"
            style={{ background: mode.gradient }}
          >
            <div className="flex items-center gap-4">
              <span className="text-4xl">{mode.icon}</span>
              <div className="flex-1">
                <div className="text-lg font-bold">{mode.title}</div>
                <div className="text-sm opacity-90 mt-1">{mode.desc}</div>
              </div>
              <span className="text-xl opacity-60">›</span>
            </div>
          </button>
        ))}
      </div>

      <CategoryDialog
        examType={examType}
        open={dialogOpen}
        onClose={() => setDialogOpen(false)}
        onSelect={handleCategorySelect}
      />
    </div>
  )
}
