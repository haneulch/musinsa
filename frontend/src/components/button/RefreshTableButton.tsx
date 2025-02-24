import { FC } from 'react';
import { Button } from 'antd';
import { useQueryClient } from '@tanstack/react-query';


interface ClearFilterProps {
  queryKey: string;
}

const RefreshTableButton: FC<ClearFilterProps> = ({queryKey}) => {
  const queryClient = useQueryClient();

  const refresh = async () => {
    await queryClient.invalidateQueries({queryKey});
  }

  return (
    <Button onClick={refresh}>Refresh Table</Button>
  )
}

export default RefreshTableButton;