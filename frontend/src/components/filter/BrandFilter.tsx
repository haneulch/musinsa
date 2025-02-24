import { useQuery } from '@tanstack/react-query';
import { GET_BRAND_LIST } from '../../pages/common/constants.ts';
import { getBrand } from '../../api/brand/brand.api.ts';

const BrandFilter = () => {
  const { data } = useQuery({
    queryKey: [GET_BRAND_LIST],
    queryFn: getBrand,
  });

  return data?.map(brand => ({
    text: brand.name,
    value: brand.id,
  })) || [];
};

export default BrandFilter;