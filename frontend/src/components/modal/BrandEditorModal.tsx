import { FC } from 'react';
import { Form, Input, Modal } from 'antd';
import { ModalProps } from '../common/common.interface.ts';
import { useMutation, useQueryClient } from '@tanstack/react-query';
import { postCreateBrand, postUpdateBrandById } from '../../api/brand/brand.api.ts';
import { GET_BRAND_LIST } from '../../pages/common/constants.ts';

const BrandEditorModal: FC<ModalProps<any>> = ({ form, open, onClose }) => {
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
    mutationFn: postCreateBrand,
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: [GET_BRAND_LIST] });
    },
  });

  const updateMutation = useMutation({
    mutationFn: postUpdateBrandById,
    onSuccess: async () => {
      await queryClient.invalidateQueries({ queryKey: [GET_BRAND_LIST] });
    },
  });

  return (
    <Modal title={ isEdit ? 'Edit Brand' : 'Add Brand' } open={ open } onOk={ onSubmit } onClose={ onClose }>
      <Form form={ form } layout="vertical">
        <Form.Item label="ID" name="id" hidden={ !isEdit }>
          <Input disabled={ isEdit } />
        </Form.Item>
        <Form.Item label="Name" name="name" rules={ [{ required: true }] }>
          <Input />
        </Form.Item>
      </Form>
    </Modal>
  );
};

export default BrandEditorModal;