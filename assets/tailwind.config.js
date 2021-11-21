const colors = require('tailwindcss/colors');

module.exports = {
    mode: 'jit',
    purge: [
      './js/**/*.js',
      '../lib/*_web/**/*.*ex'
    ],
    theme: {
      extend: {
        colors: {
          gray: colors.trueGray,
          green: colors.lime
        }
      }
    },
    variants: {},
    plugins: []
}