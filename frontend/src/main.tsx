import { ConfigProvider } from 'antd';
import { createRoot } from 'react-dom/client';
import App from './App';

const container = document.getElementById('container');

if (container) {
  createRoot(container).render(
    <ConfigProvider
      theme={ {
        token: {
          colorPrimary: '#000000',
        },
        components: {
          Table: {
            rowSelectedBg: '#e2e2e2',
            rowHoverBg: '#e2e2e2',
            rowSelectedHoverBg: '#e2e2e2',
          },
        },
      } }
    >
      <App />
    </ConfigProvider>,
  );
}
