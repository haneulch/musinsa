import { FC } from 'react';
import { Form, Input, Modal } from 'antd';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { postCreateCategory, postUpdateCategoryById } from '../../api/category/category.api.ts';
import { ModalProps } from '../common/common.interface.ts';
import { GET_CATEGORY_LIST } from '../../pages/common/constants.ts';

const CategoryEditorModal: FC<ModalProps<any>> = ({ form, open, onClose }) => {
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
    mutationFn: postCreateCategory,
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: [GET_CATEGORY_LIST] });
    },
  });

  const updateMutation = useMutation({
    mutationFn: postUpdateCategoryById,
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: [GET_CATEGORY_LIST] });
    },
  });

  return (
    <Modal title={isEdit ? 'Edit Item' : 'Add Item'} open={open} onOk={onSubmit} onCancel={onClose}>
      <Form form={form} layout="vertical">
        <Form.Item label="ID" name="id" hidden={!isEdit}>
          <Input disabled={isEdit} />
        </Form.Item>
        <Form.Item label="Code" name="code" rules={[{ required: true }]}>
          <Input disabled={isEdit} />
        </Form.Item>
        <Form.Item label="Name" name="name" rules={[{ required: true }]}>
          <Input />
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default CategoryEditorModal;
