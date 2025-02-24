import { FC } from 'react';
import { Form, Input, InputNumber, Modal } from 'antd';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { ModalProps } from '../common/common.interface.ts';
import CategorySelector from '../selector/CategorySelector.tsx';
import BrandSelector from '../selector/BrandSelector.tsx';
import { postCreateItem, postUpdateItemById } from '../../api/item/item.api.ts';
import { GET_ITEM_LIST } from '../../pages/common/constants.ts';

const ItemEditorModal: FC<ModalProps<any>> = ({ form, open, onClose }) => {
  const isEdit = !!form?.getFieldValue('id');
  const queryClient = useQueryClient();

  const onSubmit = () => {
    if (isEdit) {
      updateMutation.mutate(form.getFieldsValue());
    } else {
      createMutation.mutate(form.getFieldsValue());
    }
    form.resetFields();
    onClose();
  };

  const createMutation = useMutation({
    mutationFn: postCreateItem,
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: [GET_ITEM_LIST] });
    },
  });

  const updateMutation = useMutation({
    mutationFn: postUpdateItemById,
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: [GET_ITEM_LIST] });
    },
  });

  return (
    <Modal title={ isEdit ? 'Edit Item' : 'Add Item' } open={ open } onOk={ onSubmit } onCancel={ onClose }>
      <Form form={ form } layout="vertical">
        <Form.Item label="ID" name="id" hidden={ !isEdit }>
          <Input disabled={ isEdit } />
        </Form.Item>
        <CategorySelector />
        <BrandSelector />
        <Form.Item label="Name" name="name" rules={ [{ required: true }] }>
          <Input />
        </Form.Item>
        <Form.Item label="Price" name="price" rules={ [{ required: true }] }>
          <InputNumber />
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default ItemEditorModal;
