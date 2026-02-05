/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class', // enable class-based dark mode
  content: [
    "./src/main/resources/templates/**/*.{html,js,ts}",
    "./src/main/resources/static/**/*.{js,css}"
  ],
  theme: {
    extend: {
      colors: {
        'neutral-dark': '#1f2937',
        'neutral-dark-hover': '#374151',
        'brand': '#3b82f6',
        'brand-strong': '#2563eb',
        'fg-brand': '#3b82f6'
      },
    },
  },
  plugins: [],
}
