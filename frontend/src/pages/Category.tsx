import { FC, Key, useEffect, useState } from 'react';
import '../index.css';
import { EditOutlined, PlusOutlined } from '@ant-design/icons';
import { Button, FloatButton, PopconfirmProps, Space, Table, TableColumnsType, TableProps } from 'antd';
import { useForm } from 'antd/es/form/Form';
import CategoryEditModal from '../components/modal/CategoryEditorModal.tsx';
import ConfirmButton from '../components/button/ComfirmButtom.tsx';
import { useQuery, useQueryClient } from '@tanstack/react-query';
import { GetCategoryResponse } from '../api/category/category.interface.ts';
import { getCategory, postDeleteCategory } from '../api/category/category.api.ts';
import { GET_CATEGORY_LIST } from './common/constants.ts';

const Category: FC = () => {
  const [form] = useForm();
  const queryClient = useQueryClient();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedKeys, setSelectedKey] = useState<Key[]>([]);

  const { data } = useQuery({
    queryKey: [GET_CATEGORY_LIST],
    queryFn: getCategory,
  });

  useEffect(() => {
    setSelectedKey([]);
  }, [data]);

  const rowSelection: TableProps<GetCategoryResponse>['rowSelection'] = {
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

  const confirm: PopconfirmProps['onConfirm'] = async () => {
    await postDeleteCategory(selectedKeys);
    setSelectedKey([]);
    await queryClient.invalidateQueries({ queryKey: [GET_CATEGORY_LIST] });
  };

  const columns: TableColumnsType<GetCategoryResponse> = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: 'Code',
      dataIndex: 'code',
      key: 'code',
    },
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
    },
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
        <ConfirmButton onConfirmAction={ confirm } isDisabled={ selectedKeys.length === 0 } />
      </Space>
      <Table<GetCategoryResponse>
        rowKey="id"
        rowSelection={ { type: 'checkbox', ...rowSelection } }
        columns={ columns }
        dataSource={ data || [] }
      />

      <FloatButton icon={ <PlusOutlined /> } type="primary" style={ { insetInlineEnd: 48 } }
                   onClick={ openEditModal } />

      <CategoryEditModal form={ form } open={ isModalOpen } onClose={ cancelModal } />
    </>
  );
};

export default Category;
