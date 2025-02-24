import { FC } from 'react';
import { useQuery } from '@tanstack/react-query';
import { Space, Table, TableColumnsType } from 'antd';
import RefreshTableButton from '../../components/button/RefreshTableButton.tsx';
import { getLowestItemByCategory } from '../../api/item/item.api.ts';

const GET_CATEGORY_LOWEST_LIST = 'getLowestItemByCategory';
const CategoryLowestPrice: FC = () => {
  const { data } = useQuery({
    queryKey: [GET_CATEGORY_LOWEST_LIST],
    queryFn: getLowestItemByCategory,
  });

  const columns: TableColumnsType<any> = [
    { title: 'ID', dataIndex: 'id', key: 'id' },
    { title: 'Category', dataIndex: 'categoryName', key: 'categoryName' },
    { title: 'Brand', dataIndex: 'brandName', key: 'brandName' },
    { title: 'Price', dataIndex: 'price', key: 'price', render: (value) => value ? value.toLocaleString() : '-' },
  ];

  return (
    <>
      <Space style={ { marginBottom: 16 } }>
        <RefreshTableButton queryKey={ GET_CATEGORY_LOWEST_LIST } />
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

export default CategoryLowestPrice;
