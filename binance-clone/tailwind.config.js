/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
    "./node_modules/flowbite/**/*.js",
    "./node_modules/tw-elements/dist/js/**/*.js",
  ],
  theme: {
    extend: {
      colors: {
        "binance-mustard": "#C99400",
        "binance-yellow": "#FCD535",
        "binance-light-gray": "#FAFAFA",
        "binance-gray": "#F5F5F5",
        "binance-dark-gray": "#1E2329",
        "binance-blue": "#0689b9",
      },
    },
  },
  plugins: [require("flowbite/plugin"), require("tw-elements/dist/plugin.cjs")],
};
