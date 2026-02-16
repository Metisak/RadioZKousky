# 📻 Radio Zkoušky VFL

Aplikace pro přípravu na **radiofonní zkoušky VFL** (radiotelefonista letecké pohyblivé služby). Obsahuje kompletní sadu **164 otázek** ze všech tří zkouškových předmětů podle oficiálního katalogu ČTÚ.

## ✨ Co to umí

### 🃏 Kartičky
Procházej otázky jednu po druhé, klepnutím odhal odpověď a označ si, jestli ji umíš nebo ne. Jednoduchý a efektivní způsob, jak se s otázkami seznámit.

### ❓ Kvíz
Vyber správnou odpověď ze čtyř možností. Okamžitá zpětná vazba ti ukáže, jestli jsi odpověděl správně, a na konci vidíš celkovou úspěšnost.

### 📝 Simulace testu
Nanečisto si vyzkoušej kompletní test přesně jako u zkoušky. Všechny otázky ze všech předmětů, vyhodnocení podle pravidla **90 % v každém předmětu**. Po dokončení vidíš přehled chybných odpovědí i správné řešení.

### 🧠 Spaced Repetition
Chytré opakování založené na algoritmu SM-2 (SuperMemo). Otázky, které ti dělají problém, se ukazují častěji. Ty, které už umíš, se odkládají na později. Časem se naučíš všechno.

### 📊 Statistiky
Sleduj svůj progress — kolik otázek jsi zodpověděl, kolik jich máš naučených, jakou máš úspěšnost. Vše rozdělené podle kategorií.

## 📚 Zkouškové předměty

| Předmět | Počet otázek |
|---------|:------------:|
| 📜 Radiokomunikační předpisy | 35 |
| 🎙️ Radiokomunikační provoz | 98 |
| ⚡ Elektrotechnika a radiotechnika | 31 |
| **Celkem** | **164** |

## 📲 Instalace

1. Stáhni si APK z [nejnovějšího releasu](https://github.com/Metisak/RadioZKousky/releases/latest)
2. Na telefonu povol instalaci z neznámých zdrojů (Nastavení → Zabezpečení)
3. Otevři stažený APK a nainstaluj
4. ✅ Hotovo, můžeš se učit!

**Minimální verze Androidu:** 8.0 (API 26)

## 🛠️ Technologie

- 🟣 Kotlin + Jetpack Compose
- 🎨 Material Design 3
- 🏗️ MVVM architektura
- 🔁 Spaced Repetition (SM-2 algoritmus)
- 💾 Lokální ukládání progressu (SharedPreferences + Gson)

## 🔧 Sestavení ze zdrojového kódu

```bash
git clone https://github.com/Metisak/RadioZKousky.git
cd RadioZKousky
./gradlew assembleDebug
```

APK se vygeneruje v `app/build/outputs/apk/debug/app-debug.apk`.

## 📄 Licence

Otázky vycházejí z veřejně dostupného katalogu otázek pro zkoušky odborné způsobilosti k obsluze vysílacích rádiových zařízení, vydaného Českým telekomunikačním úřadem.
