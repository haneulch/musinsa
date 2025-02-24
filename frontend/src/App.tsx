import { Layout, Menu } from 'antd';
import { BrowserRouter as Router, Link, Route, Routes } from 'react-router-dom';
import { CheckSquareOutlined, DatabaseOutlined, PaperClipOutlined, ShoppingCartOutlined } from '@ant-design/icons';
import Category from './pages/Category.tsx';
import Item from './pages/Item.tsx';
import { FC, ReactNode, useState } from 'react';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { MenuInfo } from 'rc-menu/lib/interface';
import Brand from './pages/Brand.tsx';
import CategoryLowestPrice from './pages/shop/CategoryLowestPrice.tsx';
import CategoryMinMaxPrice from './pages/shop/CategoryMinMaxPrice.tsx';
import BrandLowestPrice from './pages/shop/BrandLowestPrice.tsx';

const { Header, Content, Sider } = Layout;
const queryClient = new QueryClient();

interface MenuItem {
  key: string;
  link?: string;
  name: string;
  title: string;
  icon: ReactNode;
  subMenus?: MenuItem[];
}

const menuList: MenuItem[] = [
  { key: 'category', name: 'Category', title: '카테고리', icon: <PaperClipOutlined /> },
  { key: 'item', name: 'Item', title: '상품', icon: <DatabaseOutlined /> },
  { key: 'brand', name: 'Brand', title: '브랜드', icon: <CheckSquareOutlined /> },
  {
    key: 'shop',
    name: 'Lowest Price',
    title: '최저가격',
    icon: <ShoppingCartOutlined />,
    subMenus: [
      {
        key: 'shop-1',
        link: 'view/shop/lowest/category',
        name: 'Category',
        title: '카테고리별 최저가격',
        icon: <PaperClipOutlined />,
      },
      {
        key: 'shop-2',
        link: 'view/shop/minmax/category',
        name: 'Item',
        title: '단일 카테고리 최저가격/최고가격',
        icon: <DatabaseOutlined />,
      },
      {
        key: 'shop-3',
        link: 'view/shop/lowest/brand',
        name: 'Brand',
        title: '단일 브랜드 최저가격',
        icon: <CheckSquareOutlined />,
      },
    ],
  },
];

const App: FC = () => {
  const [menu, setMenu] = useState<MenuItem>(menuList[0]);

  const menuClick = (e: MenuInfo) => {
    const selected =
      menuList.flatMap((item) => (item.subMenus ? [item, ...item.subMenus] : [item])).find((m) => m.key == e.key) ||
      menuList[0];
    setMenu(selected);
  };

  return (
    <QueryClientProvider client={queryClient}>
      <Router>
        <Layout style={{ minHeight: '100vh' }}>
          {/* 사이드바 네비게이션 */}
          <Sider collapsible>
            <div
              className="logo"
              style={{
                color: 'white',
                textAlign: 'center',
                padding: '16px',
                fontSize: '20px',
              }}
            >
              MUSINSA
            </div>
            <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']} onClick={menuClick}>
              {menuList.map((item) => {
                return item.subMenus ? (
                  <Menu.SubMenu key={item.key} icon={item.icon} title={item.name}>
                    {item.subMenus.map((sub) => {
                      return (
                        <Menu.Item key={sub.key} icon={sub.icon}>
                          <Link to={sub.link || `view/${sub.key}`}>{sub.name}</Link>
                        </Menu.Item>
                      );
                    })}
                  </Menu.SubMenu>
                ) : (
                  <Menu.Item key={item.key} icon={item.icon}>
                    <Link to={item.link || `view/${item.key}`}>{item.name}</Link>
                  </Menu.Item>
                );
              })}
            </Menu>
          </Sider>

          {/* 메인 콘텐츠 영역 */}
          <Layout>
            <Header
              style={{
                background: '#fff',
                padding: 0,
                textAlign: 'center',
                fontSize: '20px',
              }}
            >
              🚀 {menu.title}
            </Header>
            <Content
              style={{
                margin: '16px',
                padding: '24px',
                background: '#fff',
                minHeight: '280px',
              }}
            >
              <Routes>
                <Route path="/view/category" element={<Category />} />
                <Route path="/view/item" element={<Item />} />
                <Route path="/view/brand" element={<Brand />} />
                <Route path="/view/shop/lowest/category" element={<CategoryLowestPrice />} />
                <Route path="/view/shop/minmax/category" element={<CategoryMinMaxPrice />} />
                <Route path="/view/shop/lowest/brand" element={<BrandLowestPrice />} />
              </Routes>
            </Content>
          </Layout>
        </Layout>
      </Router>
    </QueryClientProvider>
  );
};

export default App;
