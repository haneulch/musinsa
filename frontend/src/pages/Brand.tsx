import { FC, Key, useEffect, useState } from 'react';
import { useQuery, useQueryClient } from '@tanstack/react-query';
import { Button, FloatButton, PopconfirmProps, Space, Table, TableColumnsType, TableProps } from 'antd';
import { EditOutlined, PlusOutlined } from '@ant-design/icons';
import { getBrand, postDeleteBrand } from '../api/brand/brand.api.ts';
import { GetBrandResponse } from '../api/brand/brand.interface.ts';
import BrandEditorModal from '../components/modal/BrandEditorModal.tsx';
import { useForm } from 'antd/es/form/Form';
import ConfirmButton from '../components/button/ComfirmButtom.tsx';
import { GET_BRAND_LIST } from './common/constants.ts';

const Brand: FC = () => {
  const [form] = useForm();
  const queryClient = useQueryClient();

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedKeys, setSelectedKey] = useState<Key[]>([]);

  const { data } = useQuery({
    queryKey: [GET_BRAND_LIST],
    queryFn: getBrand,
  });

  useEffect(() => {
    setSelectedKey([]);
  }, [data]);

  const rowSelection: TableProps<GetBrandResponse>['rowSelection'] = {
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
    await postDeleteBrand(selectedKeys);
    setSelectedKey([]);
    await queryClient.invalidateQueries({ queryKey: [GET_BRAND_LIST] });
  };

  const columns: TableColumnsType<GetBrandResponse> = [
    { title: 'ID', dataIndex: 'id', key: 'id' },
    { title: 'Brand Name', dataIndex: 'name', key: 'name' },
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
      <Table<GetBrandResponse>
        rowKey="id"
        rowSelection={ { type: 'checkbox', ...rowSelection } }
        columns={ columns }
        dataSource={ data || [] }
      />

      <FloatButton icon={ <PlusOutlined /> } type="primary" style={ { insetInlineEnd: 48 } }
                   onClick={ openEditModal } />

      <BrandEditorModal form={ form } open={ isModalOpen } onClose={ cancelModal } />
    </>
  );
};

export default Brand;
