import { Routes, Route } from 'react-router-dom'
import ExamSelect from './pages/ExamSelect'
import Home from './pages/Home'
import Flashcards from './pages/Flashcards'
import Quiz from './pages/Quiz'
import Test from './pages/Test'
import Stats from './pages/Stats'

export default function App() {
  return (
    <div className="mx-auto max-w-lg min-h-screen">
      <Routes>
        <Route path="/" element={<ExamSelect />} />
        <Route path="/home/:examType" element={<Home />} />
        <Route path="/flashcards/:examType/:category" element={<Flashcards mode="flashcards" />} />
        <Route path="/spaced/:examType/:category" element={<Flashcards mode="spaced" />} />
        <Route path="/quiz/:examType/:category" element={<Quiz />} />
        <Route path="/test/:examType" element={<Test />} />
        <Route path="/stats/:examType" element={<Stats />} />
      </Routes>
    </div>
  )
}
