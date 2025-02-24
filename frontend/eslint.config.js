import globals from 'globals';
import tseslint from 'typescript-eslint';
import pluginReact from 'eslint-plugin-react';
import pluginReactHooks from 'eslint-plugin-react-hooks';
import pluginPrettier from 'eslint-plugin-prettier';

/** @type {import('eslint').Linter.FlatConfig[]} */
export default [
  {
    files: ['**/*.{js,mjs,cjs,ts,jsx,tsx}'],
    languageOptions: {
      globals: globals.browser,
      ecmaVersion: 'latest',
      sourceType: 'module',
    },
    settings: {
      react: {
        version: 'detect',
      },
    },
  },
  ...tseslint.configs.recommended,
  pluginReact.configs.flat.recommended,
  pluginReactHooks.configs.recommended,
  {
    plugins: {
      prettier: pluginPrettier,
    },
    rules: {
      'prettier/prettier': 'warn',
    },
  },
  {
    rules: {
      'react/react-in-jsx-scope': 'off', // Next.js 사용 시 불필요
      '@typescript-eslint/no-unused-vars': ['warn', { argsIgnorePattern: '^_' }], // 미사용 변수 경고
      'react-hooks/rules-of-hooks': 'error', // React Hooks 규칙 강제
      'react-hooks/exhaustive-deps': 'warn', // useEffect 종속성 검사
    },
  },
];
