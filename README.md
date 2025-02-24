# musinsa

![Static Badge](https://img.shields.io/badge/Spring_Boot-3.4.2-green)
![Static Badge](https://img.shields.io/badge/Java-17-red)
![Static Badge](https://img.shields.io/badge/React-19-blue)

![Static Badge](https://img.shields.io/badge/H2-blue)
![Static Badge](https://img.shields.io/badge/Caffeine_Cache-yellow)
![Static Badge](https://img.shields.io/badge/Maven-gray)

무신사 전시개발팀 Backend Engineer 과제

## Backend
***

- **API**
  - 브랜드 관리
    - /api/brand/**
  - 카테고리 관리
    - /api/category/**
  - 아이템 관리
    - /api/item/**


- 실행 > [Spring Boot](./src/main/java/com/musinsa/catalog/CatalogApplication.java) 실행(8080)
- 백엔드 packaing 및 실행 테스트

```bash
  # maven packaging
  > mvn clean install package -DskipTests
```

```bash
  # jar 실행 (local) - localhost:3000 으로 front 접근 가능
  > java -jar target/catalog.jar
```
- 테스트 케이스
  - Controller
    - [BrandControllerTests](./src/test/java/com/musinsa/catalog/brand/web/BrandControllerTests.java)
  - Service
    - [BrandServiceTests.java](./src/test/java/com/musinsa/catalog/brand/service/BrandServiceTests.java)
    - [CategoryServiceTests.java](./src/test/java/com/musinsa/catalog/category/service/CategoryServiceTests.java)
  - Repository
    - [BrandRepositoryTests.java](./src/test/java/com/musinsa/catalog/persistence/repository/BrandRepositoryTests.java)
    - [CategoryRepositoryTests.java](./src/test/java/com/musinsa/catalog/persistence/repository/CategoryRepositoryTests.java)
    - [ItemRepositoryTests.java](./src/test/java/com/musinsa/catalog/persistence/repository/ItemRepositoryTests.java)

## Frontend

***

- Frontend > vite 실행(3000)

```bash
  # Frontend start script
  > cd frontend
  > nvm use
  > npm i
  > npm run build
  > npm run dev
```

## 개발시 참고사항

***

- frontend에서 backend를 호출하는 경우, **/api**로 시작하는 URL 사용
    - [vite 설정](./frontend/vite.config.ts)에 의해 **/api**로 시작하는 요청은 backend로 포워딩 됨
- api error code ([참조](./src/main/java/com/musinsa/catalog/common/code/ErrorCode.java))