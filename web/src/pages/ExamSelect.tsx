import { useNavigate } from 'react-router-dom'
import { ExamType } from '../data/types'

export default function ExamSelect() {
  const navigate = useNavigate()

  return (
    <div className="flex flex-col min-h-screen px-6 py-12">
      <div className="flex flex-col items-center mb-12">
        <div className="text-6xl mb-4">📡</div>
        <h1 className="text-3xl font-bold text-center dark:text-white">Radio Zkoušky</h1>
        <p className="text-gray-500 dark:text-gray-400 mt-2 text-center">
          Vyber typ průkazu, na který se chceš připravit
        </p>
      </div>

      <div className="flex flex-col gap-5 flex-1">
        <button
          onClick={() => navigate(`/home/${ExamType.VFL}`)}
          className="w-full rounded-2xl p-6 text-left text-white shadow-lg active:scale-[0.98] transition-transform"
          style={{ background: 'linear-gradient(135deg, #1565C0, #1976D2)' }}
        >
          <div className="flex items-center gap-4">
            <span className="text-4xl">🌍</span>
            <div className="flex-1">
              <div className="text-2xl font-bold">VFL</div>
              <div className="text-sm opacity-85">Všeobecný průkaz radiotelefonisty letecké pohyblivé služby</div>
            </div>
            <span className="text-2xl opacity-60">›</span>
          </div>
          <div className="flex justify-between items-end mt-4">
            <div className="text-sm opacity-90">
              Anglické fráze a terminologie
              <br />
              ROGER, WILCO, MAYDAY...
            </div>
            <span className="bg-white/20 rounded-xl px-3 py-1 text-sm font-bold">164 otázek</span>
          </div>
        </button>

        <button
          onClick={() => navigate(`/home/${ExamType.OFL}`)}
          className="w-full rounded-2xl p-6 text-left text-white shadow-lg active:scale-[0.98] transition-transform"
          style={{ background: 'linear-gradient(135deg, #2E7D32, #388E3C)' }}
        >
          <div className="flex items-center gap-4">
            <span className="text-4xl">🇨🇿</span>
            <div className="flex-1">
              <div className="text-2xl font-bold">OFL</div>
              <div className="text-sm opacity-85">Omezený průkaz radiotelefonisty letecké pohyblivé služby</div>
            </div>
            <span className="text-2xl opacity-60">›</span>
          </div>
          <div className="flex justify-between items-end mt-4">
            <div className="text-sm opacity-90">
              České fráze a terminologie
              <br />
              ROZUMÍM, PROVEDU, ČEKEJTE...
            </div>
            <span className="bg-white/20 rounded-xl px-3 py-1 text-sm font-bold">149 otázek</span>
          </div>
        </button>
      </div>

      <p className="text-center text-sm text-gray-400 dark:text-gray-500 mt-8">
        Splnění zkoušky: 90 % v každém předmětu
      </p>
    </div>
  )
}
