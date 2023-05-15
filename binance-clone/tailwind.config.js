/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        'mustard': '#C99400',
        'yellow': '#FCD535',
        'light-gray': '#FAFAFA',
        'gray': '#F5F5F5',
        'dark-gray': '#1E2329'
      }
    },
  },
  plugins: [],
}

