# musinsa
![Static Badge](https://img.shields.io/badge/Spring_Boot-3.4.2-green)
![Static Badge](https://img.shields.io/badge/Java-17-red)
![Static Badge](https://img.shields.io/badge/React-19-blue)

무신사 전시개발팀 Backend Engineer 과제

## Backend
***
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