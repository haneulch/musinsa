import { Dispatch, FC, SetStateAction } from 'react';
import { Button } from 'antd';
import { FilterValue } from 'antd/es/table/interface';


interface ClearFilterProps {
  clearFilterAction : Dispatch<SetStateAction<Record<string, FilterValue|null>>>
}

const ClearFilterButton: FC<ClearFilterProps> = ({clearFilterAction}) => {

  const clearFilter = () => {
    clearFilterAction({});
  };

  return (
    <Button onClick={clearFilter}>Clear Filter</Button>
  )
}

export default ClearFilterButton;