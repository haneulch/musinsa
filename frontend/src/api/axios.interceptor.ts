import axios from 'axios';
import { TEMP_TOKEN } from '../config/constants.ts';

// Axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// 요청 인터셉터: JWT 자동 추가
apiClient.interceptors.request.use(
  (config) => {
    config.headers.Authorization = `Bearer ${ TEMP_TOKEN }`;
    return config;
  },
  (error) => Promise.reject(error),
);

export default apiClient;