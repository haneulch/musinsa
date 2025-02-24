export interface GetCategoryResponse {
  id: number;
  code: string;
  name: string;
}

export interface PostCreateCategoryRequest {
  code: string;
  name: string;
  createdId: string;
}

export interface PostUpdateCategoryByIdRequest {
  id: number;
  name: string;
  updatedId: string;
}