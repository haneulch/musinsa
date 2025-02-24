import { Form, Select } from 'antd';
import { CSSProperties, FC } from 'react';
import { useQuery } from '@tanstack/react-query';
import { getCategory } from '../../api/category/category.api.ts';
import { GET_CATEGORY_LIST } from '../../pages/common/constants.ts';

interface CategorySelectorProps {
  style?: CSSProperties;
  onChange?: (code: string) => void;
  defaultValue?: string;
}

const CategorySelector: FC<CategorySelectorProps> = ({ style, onChange, defaultValue }) => {
  const { data } = useQuery({
    queryKey: [GET_CATEGORY_LIST],
    queryFn: getCategory,
  });

  return (
    <>
      <Form.Item label="Category" name="categoryCode" rules={ [{ required: true }] } style={ style }>
        <Select value={ defaultValue } placeholder="category" onChange={ (value) => onChange?.(value) }>
          { data?.map((item) => (
            <Select.Option key={ item.code } value={ item.code }>
              { item.name }
            </Select.Option>
          )) }
        </Select>
      </Form.Item>
    </>
  );
};

export default CategorySelector;
