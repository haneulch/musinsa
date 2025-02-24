import { responseHandler } from '../common.handler.ts';
import { GetCategoryResponse, PostCreateCategoryRequest, PostUpdateCategoryByIdRequest } from './category.interface.ts';
import { Key } from 'react';
import apiClient from '../axios.interceptor.ts';
import axios from 'axios';

const PREFIX = '/category';

export const getCategory = async () => {
  return axios.get(`/api${PREFIX}`).then<GetCategoryResponse[]>(responseHandler);
};

export const postCreateCategory = async (params: PostCreateCategoryRequest) => {
  return apiClient.post(`${PREFIX}/create`, params).then<void>(responseHandler);
};

export const postUpdateCategoryByCode = async ({ code, ...params }: PostUpdateCategoryByIdRequest) => {
  return apiClient.post(`${PREFIX}/update/${code}`, params).then<void>(responseHandler);
};
export const postDeleteCategory = async (ids: Key[]) => {
  return apiClient.post(`${PREFIX}/delete`, { ids }).then<void>(responseHandler);
};