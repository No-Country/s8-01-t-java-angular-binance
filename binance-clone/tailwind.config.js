/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        'binance-mustard': '#C99400',
        'binance-yellow': '#FCD535',
        'binance-light-gray': '#FAFAFA',
        'binance-gray': '#F5F5F5',
        'binance-dark-gray': '#1E2329'
      }
    },
  },
  plugins: [],
}

