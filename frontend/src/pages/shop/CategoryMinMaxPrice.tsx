import { FC, useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { Space, Table, TableColumnsType } from 'antd';
import RefreshTableButton from '../../components/button/RefreshTableButton.tsx';
import { getMinMaxItemByCategory } from '../../api/item/item.api.ts';
import CategorySelector from '../../components/selector/CategorySelector.tsx';

const GET_CATEGORY_MINMAX_LIST = 'getMinMaxItemByCategory';
const CategoryMinMaxPrice: FC = () => {
  const [categoryCode, setCategoryCode] = useState<string>('top');

  const { data } = useQuery({
    queryKey: [GET_CATEGORY_MINMAX_LIST],
    queryFn: () => getMinMaxItemByCategory(categoryCode),
  });

  const columns: TableColumnsType<any> = [
    { title: 'ID', dataIndex: 'id', key: 'id' },
    { title: 'Category', dataIndex: 'categoryName', key: 'categoryName' },
    { title: 'Brand', dataIndex: 'brandName', key: 'brandName' },
    { title: 'Price', dataIndex: 'price', key: 'price', render: (value) => value ? value.toLocaleString() : '-' },
  ];

  const categoryChangeAction = (code: string) => {
    setCategoryCode(code);
  };

  return (
    <>
      <Space style={ { marginBottom: 16 } }>
        <CategorySelector defaultValue={ categoryCode } style={ { margin: 0 } } onChange={ categoryChangeAction } />
        <RefreshTableButton queryKey={ GET_CATEGORY_MINMAX_LIST } />
      </Space>
      <Table
        rowKey="id"
        columns={ columns }
        dataSource={ data?.items }
        pagination={ false }
        summary={ () => (
          <Table.Summary.Row>
            <Table.Summary.Cell index={ 0 } colSpan={ 3 }>
              Total
            </Table.Summary.Cell>
            <Table.Summary.Cell index={ 1 }>
              { data?.totalPrice.toLocaleString() }
            </Table.Summary.Cell>
          </Table.Summary.Row>
        ) }
      />
    </>
  );
};

export default CategoryMinMaxPrice;
