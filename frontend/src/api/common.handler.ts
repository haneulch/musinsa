import { AxiosResponse } from 'axios';
import { TEMP_TOKEN } from '../config/constants.ts';

export const responseHandler = <T>(response: AxiosResponse<T>): T => response.data;

export const header = () => {
  return {
    headers: { Authorization: `Bearer ${ TEMP_TOKEN }` },
  };
};