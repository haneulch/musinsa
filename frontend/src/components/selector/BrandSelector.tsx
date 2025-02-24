import { Form, Select } from 'antd';
import { FC } from 'react';
import { useQuery } from '@tanstack/react-query';
import { getBrand } from '../../api/brand/brand.api.ts';
import { GET_BRAND_LIST } from '../../pages/common/constants.ts';

const BrandSelector: FC = () => {
  const { data } = useQuery({
    queryKey: [GET_BRAND_LIST],
    queryFn: getBrand,
  });

  return (
    <>
      <Form.Item label="Brand" name="brandId" rules={ [{ required: true }] }>
        <Select placeholder="brand">
          { data?.map((item) => (
            <Select.Option key={ item.id } value={ item.id }>
              { item.name }
            </Select.Option>
          )) }
        </Select>
      </Form.Item>
    </>
  );
};

export default BrandSelector;
