import { useQuery } from '@tanstack/react-query';
import { getCategory } from '../../api/category/category.api.ts';
import { GET_CATEGORY_LIST } from '../../pages/common/constants.ts';

const CategoryFilter = () => {
  const { data } = useQuery({
    queryKey: [GET_CATEGORY_LIST],
    queryFn: getCategory,
  });

  return data?.map(category => ({
    text: category.name,
    value: category.code,
  })) || [];
};

export default CategoryFilter;