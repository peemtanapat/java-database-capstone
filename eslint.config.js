export default [
  {
    files: ["app/src/main/resources/static/js/**/*.js"],
    languageOptions: {
      ecmaVersion: "latest",
      sourceType: "module",
    },
    rules: {
      "no-unused-vars": "warn",
      "no-undef": "error",
      eqeqeq: "error",
      curly: "error",
      "no-console": "warn",
      semi: ["error", "always"],
      quotes: ["error", "double"],
      indent: ["error", 2],
    },
  },
];
