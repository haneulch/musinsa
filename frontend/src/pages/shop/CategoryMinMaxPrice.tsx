import { FC, useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { Space, Table, TableColumnsType } from 'antd';
import RefreshTableButton from '../../components/button/RefreshTableButton.tsx';
import { getMinMaxItemByCategory } from '../../api/item/item.api.ts';
import CategorySelector from '../../components/selector/CategorySelector.tsx';

const GET_CATEGORY_MINMAX_LIST = 'getMinMaxItemByCategory';

const CategoryMinMaxPrice: FC = () => {
  const [categoryCode, setCategoryCode] = useState<string>('top');

  const { data, refetch } = useQuery({
    queryKey: [GET_CATEGORY_MINMAX_LIST, categoryCode],
    queryFn: () => getMinMaxItemByCategory(categoryCode),
  });

  const tableData = [
    ...(data?.lowestItems.map((item) => ({ ...item, type: '최저가' })) || []),
    ...(data?.highestItems.map((item) => ({ ...item, type: '최고가' })) || []),
  ];

  const columns: TableColumnsType<any> = [
    { title: 'Type', dataIndex: 'type', key: 'type' },
    { title: 'Brand', dataIndex: 'brandName', key: 'brandName' },
    { title: 'Price', dataIndex: 'price', key: 'price', render: (value) => (value ? value.toLocaleString() : '-') },
  ];

  const categoryChangeAction = async (code: string) => {
    setCategoryCode(code);
    await refetch();
  };

  return (
    <>
      <Space style={{ marginBottom: 16 }}>
        <CategorySelector defaultValue={categoryCode} style={{ margin: 0 }} onChange={categoryChangeAction} />
        <RefreshTableButton queryKey={GET_CATEGORY_MINMAX_LIST} />
      </Space>
      <Table
        rowKey={(record) => `${record.type}-${record.id}`}
        columns={columns}
        dataSource={tableData}
        pagination={false}
      />
    </>
  );
};

export default CategoryMinMaxPrice;
