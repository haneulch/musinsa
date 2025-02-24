import axios from 'axios';
import { TEMP_TOKEN } from '../config/constants.ts';

// Axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: '/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// 요청 Interceptor: JWT 자동 추가
apiClient.interceptors.request.use(
  (config) => {
    config.headers.Authorization = `Bearer ${TEMP_TOKEN}`;
    return config;
  },
  (error) => {
    console.error({ message: '요청 중 오류가 발생했습니다.' });
    return Promise.reject(error);
  },
);

// 응답 Interceptor
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      const { status, data } = error.response;
      console.error({
        message: `[${data?.code || '9999'}] 에러 발생 (${status}): ${data?.message || '서버 오류'}`,
      });
    } else {
      console.error({ message: '네트워크 오류 또는 서버 응답 없음.' });
    }
    return Promise.reject(error);
  },
);

export default apiClient;
