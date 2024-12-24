/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    colors: {
      red: "#ffb3ba",
      orange: "#ffe4b2",
      green: "#c1e1c1",
      blue: "#d0f0fd",
      purple: "#cabdff",
      black: "#1c1c1c",
    },
    fontFamily: {
      sans: ["Lato", "sans-serif"],
    },
    extend: {},
  },
  plugins: [],
};
