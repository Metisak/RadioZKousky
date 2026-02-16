import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import { VitePWA } from 'vite-plugin-pwa'

export default defineConfig({
  base: '/RadioZKousky/',
  plugins: [
    react(),
    VitePWA({
      registerType: 'autoUpdate',
      includeAssets: ['icon.svg'],
      manifest: {
        name: 'Radio Zkoušky',
        short_name: 'RadioZK',
        description: 'Příprava na radiofonní zkoušky VFL a OFL',
        theme_color: '#1565C0',
        background_color: '#f8fafb',
        display: 'standalone',
        orientation: 'portrait',
        scope: '/RadioZKousky/',
        start_url: '/RadioZKousky/',
        icons: [
          { src: 'icon.svg', sizes: 'any', type: 'image/svg+xml', purpose: 'any maskable' }
        ]
      },
      workbox: {
        globPatterns: ['**/*.{js,css,html,ico,png,svg}']
      }
    })
  ]
})
