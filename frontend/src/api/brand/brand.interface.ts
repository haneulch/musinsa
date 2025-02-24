export interface GetBrandResponse {
  id: number;
  name: string;
}

export interface PostCreateBrandRequest {
  name: string;
}

export interface PostUpdateBrandByIdRequest {
  id: number;
  name: string;
}