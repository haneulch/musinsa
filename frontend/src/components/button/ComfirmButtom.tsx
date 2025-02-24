import { FC } from 'react';
import { Button, Popconfirm } from 'antd';

interface ConfirmButtonProps {
  title?: string;
  name?: string;
  onConfirmAction: () => void;
  isDisabled?: boolean;
}

const ConfirmButton: FC<ConfirmButtonProps> = ({ title, name, onConfirmAction, isDisabled }) => {
  return (
    <>
      <Popconfirm title={title || 'Are you sure you want to delete?'} onConfirm={onConfirmAction} disabled={isDisabled}>
        <Button disabled={isDisabled}>{name || 'Delete Item'}</Button>
      </Popconfirm>
    </>
  );
};

export default ConfirmButton;
