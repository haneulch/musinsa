export interface GetItemResponse {
  id: number;
  name: string;
  brandId: number;
  brandName: string;
  categoryCode: string;
  categoryName: string;
  price: number;
}

interface ItemElement {
  id: number;
  brandName: string;
  categoryName: string;
  price: number;
}

export interface GetLowestItemResponse {
  items: ItemElement[];
  totalPrice: number;
}

export interface GetMinMaxItemResponse {
  lowestItems: ItemElement[];
  highestItems: ItemElement[];
}

export interface PostCreateItemRequest {
  name: string;
  brandId: number;
  categoryCode: string;
  price: number;
}

export interface PostUpdateItemByIdRequest {
  id: number;
  name: string;
  brandId: number;
  categoryCode: string;
  price: number;
}
