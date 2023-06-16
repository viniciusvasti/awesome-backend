// eslint-disable-next-line no-undef
module.exports = {
  env: {
    node: true,
    es2021: true,
    jest: true,
  },
  extends: ["eslint:recommended", "plugin:@typescript-eslint/recommended"],
  parser: "@typescript-eslint/parser",
  parserOptions: {
    ecmaVersion: "latest",
    sourceType: "module",
  },
  plugins: ["@typescript-eslint"],
  rules: {
    indent: ["error", 2],
    "linebreak-style": ["error", "unix"],
    quotes: ["error", "single"],
    semi: ["error", "always"],
    "no-console": "error",
    "no-undef": "error",
    "@typescript-eslint/no-unused-vars": ["error"],
  },
  ignorePatterns: [".eslintrc.cjs", "jest.config.js", "dist/"],
};
