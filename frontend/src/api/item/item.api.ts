import { responseHandler } from '../common.handler.ts';
import {
  GetItemResponse,
  GetLowestItemResponse,
  PostCreateItemRequest,
  PostUpdateItemByIdRequest,
} from './item.interface.ts';
import { Key } from 'react';
import apiClient from '../axios.interceptor.ts';

const PREFIX = '/item';

export const getItem = async () => {
  return apiClient.get(PREFIX).then<GetItemResponse[]>(responseHandler);
};

export const getLowestItemByCategory = async () => {
  return apiClient.get(`${ PREFIX }/lowest/category`).then<GetLowestItemResponse>(responseHandler);
};

export const getLowestItemByBrand = async () => {
  return apiClient.get(`${ PREFIX }/lowest/brand`).then<GetLowestItemResponse>(responseHandler);
};

export const getMinMaxItemByCategory = async (categoryCode: string) => {
  return apiClient.get(`${ PREFIX }/minmax/category/${ categoryCode }`).then<GetLowestItemResponse>(responseHandler);
};

export const postCreateItem = async (params: PostCreateItemRequest) => {
  return apiClient.post(`${ PREFIX }/create`, params).then(responseHandler);
};

export const postUpdateItemById = async ({ id, ...params }: PostUpdateItemByIdRequest) => {
  return apiClient.post(`${ PREFIX }/update/${ id }`, params).then(responseHandler);
};

export const postDeleteItem = async (ids: Key[]) => {
  return apiClient.post(`${ PREFIX }/delete`, { ids }).then(responseHandler);
};