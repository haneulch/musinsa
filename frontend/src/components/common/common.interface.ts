import { FormInstance } from 'antd';

export interface ModalProps<T> {
  form: FormInstance<T>;
  open: boolean;
  onClose: () => void;
}