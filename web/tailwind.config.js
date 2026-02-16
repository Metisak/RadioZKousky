/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  darkMode: 'media',
  theme: {
    extend: {
      colors: {
        primary: { DEFAULT: '#1565C0', light: '#1976D2', dark: '#0D47A1' },
        secondary: { DEFAULT: '#00796B', light: '#009688' },
        tertiary: { DEFAULT: '#EF6C00', light: '#FF9800' },
        predpisy: '#1565C0',
        provoz: '#2E7D32',
        elektro: '#EF6C00',
        correct: '#4CAF50',
        incorrect: '#F44336',
      }
    }
  },
  plugins: []
}
