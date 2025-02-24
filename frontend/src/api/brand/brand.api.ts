import { responseHandler } from '../common.handler.ts';
import { GetBrandResponse, PostCreateBrandRequest, PostUpdateBrandByIdRequest } from './brand.interface.ts';
import { Key } from 'react';
import apiClient from '../axios.interceptor.ts';

const PREFIX = '/brand';

export const getBrand = async () => {
  return apiClient.get(PREFIX, { headers: {} }).then<GetBrandResponse[]>(responseHandler);
};

export const postCreateBrand = async (params: PostCreateBrandRequest) => {
  return apiClient.post(`${ PREFIX }/create`, params).then(responseHandler);
};

export const postUpdateBrandById = async ({ id, ...params }: PostUpdateBrandByIdRequest) => {
  return apiClient.post(`${ PREFIX }/update/${ id }`, params).then(responseHandler);
};

export const postDeleteBrand = async (ids: Key[]) => {
  return apiClient.post(`${ PREFIX }/delete`, { ids }).then(responseHandler);
};