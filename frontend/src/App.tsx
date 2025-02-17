import { Button, ConfigProvider } from 'antd'
import './App.css'

function App() {

  return (
    <>
      <ConfigProvider
        theme={ {
          token: {
            colorPrimary: '#000000',
          },
        } }
      >
        <div style={ { padding: 20 } }>
          <h1>React + Ant Design + Spring Boot</h1>
          <p>Backend Response: </p>
          <Button type="primary">Click Me</Button>
        </div>
      </ConfigProvider>
    </>
  );
}

export default App
