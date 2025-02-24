import { FC, Key, useEffect, useState } from 'react';
import { useQuery, useQueryClient } from '@tanstack/react-query';
import { Button, FloatButton, PopconfirmProps, Space, Table, TableColumnsType, TableProps } from 'antd';
import { EditOutlined, PlusOutlined } from '@ant-design/icons';
import { getItem, postDeleteItem } from '../api/item/item.api.ts';
import { GetItemResponse } from '../api/item/item.interface.ts';
import CategoryFilter from '../components/filter/CategoryFilter.tsx';
import ClearFilterButton from '../components/button/ClearFilterButton.tsx';
import ItemEditorModal from '../components/modal/ItemEditorModal.tsx';
import { useForm } from 'antd/es/form/Form';
import ConfirmButton from '../components/button/ComfirmButtom.tsx';
import { GET_ITEM_LIST } from './common/constants.ts';
import BrandFilter from '../components/filter/BrandFilter.tsx';

type OnChange = NonNullable<TableProps<GetItemResponse>['onChange']>;
type Filters = Parameters<OnChange>[1];

const Item: FC = () => {
  const [form] = useForm();
  const queryClient = useQueryClient();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [filteredInfo, setFilteredInfo] = useState<Filters>({});
  const [selectedKeys, setSelectedKey] = useState<Key[]>([]);

  const { data } = useQuery({
    queryKey: [GET_ITEM_LIST],
    queryFn: getItem,
  });

  useEffect(() => {
    setFilteredInfo({});
    setSelectedKey([]);
  }, [data]);

  const rowSelection: TableProps<GetItemResponse>['rowSelection'] = {
    selectedRowKeys: selectedKeys,
    onChange: (selectedRowKeys: Key[]) => {
      setSelectedKey(selectedRowKeys);
    },
  };

  const openEditModal = (record: unknown) => {
    form.setFieldsValue(record);
    setIsModalOpen(true);
  };

  const cancelModal = () => {
    form.resetFields();
    setIsModalOpen(false);
  };

  const applyFilter: OnChange = (_, filters) => {
    setFilteredInfo(filters);
  };

  const confirm: PopconfirmProps['onConfirm'] = async () => {
    await postDeleteItem(selectedKeys);
    setSelectedKey([]);
    await queryClient.invalidateQueries({ queryKey: [GET_ITEM_LIST] });
  };

  const columns: TableColumnsType<GetItemResponse> = [
    { title: 'ID', dataIndex: 'id', key: 'id' },
    {
      title: 'Category', dataIndex: 'categoryName', key: 'categoryCode',
      filters: CategoryFilter(),
      filteredValue: filteredInfo.categoryCode || null,
      onFilter: (value, record) => record.categoryCode === value,

    },
    {
      title: 'Brand Name', dataIndex: 'brandName', key: 'brandId',
      filters: BrandFilter(),
      filteredValue: filteredInfo.brandId || null,
      onFilter: (value, record) => record.brandId === value,

    },
    { title: 'Name', dataIndex: 'name', key: 'name' },
    { title: 'Price', dataIndex: 'price', key: 'price' },
    {
      title: 'Edit',
      key: 'key',
      render: (_, record) => (
        <Button icon={ <EditOutlined /> } onClick={ () => openEditModal(record) }>
          Edit
        </Button>
      ),
    },
  ];

  return (
    <>
      <Space style={ { marginBottom: 16 } }>
        <ClearFilterButton clearFilterAction={ setFilteredInfo } />
        <ConfirmButton onConfirmAction={ confirm } isDisabled={ selectedKeys.length === 0 } />
      </Space>
      <Table<GetItemResponse>
        rowKey="id"
        rowSelection={ { type: 'checkbox', ...rowSelection } }
        columns={ columns }
        dataSource={ data || [] }
        onChange={ applyFilter }
      />

      <FloatButton icon={ <PlusOutlined /> } type="primary" style={ { insetInlineEnd: 48 } }
                   onClick={ openEditModal } />

      <ItemEditorModal form={ form } open={ isModalOpen } onClose={ cancelModal } />
    </>
  );
};

export default Item;
