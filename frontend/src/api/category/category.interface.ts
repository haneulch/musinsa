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
  code: string;
  name: string;
  updatedId: string;
}