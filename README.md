# 간단한 게시판 애플리케이션

Java 17 + Spring Boot 백엔드와 React 18 프론트엔드로 구성된 간단한 게시판 애플리케이션입니다.

## 기술 스택

### 백엔드
- Java 17 (Amazon Corretto)
- Spring Boot 3.2.0
- Spring Security
- JWT 인증
- MyBatis
- MySQL 8.x
- Gradle

### 프론트엔드
- React 18.2.0
- React Router DOM
- Axios
- CSS3

## 기능

- 사용자 회원가입/로그인
- JWT 기반 인증
- 게시글 작성/조회/삭제
- 마이페이지 (내 게시글 관리)
- 반응형 UI

## 설치 및 실행

### 1. 데이터베이스 설정

MySQL 8.x를 설치하고 다음 설정으로 데이터베이스를 생성하세요:

```sql
CREATE DATABASE myappdb;
```

### 2. 백엔드 실행

```bash
cd backend
./gradlew bootRun
```

백엔드는 `http://localhost:8080`에서 실행됩니다.

### 3. 프론트엔드 실행

```bash
cd frontend
npm install
npm start
```

프론트엔드는 `http://localhost:3000`에서 실행됩니다.

## API 엔드포인트

### 인증
- `POST /api/auth/register` - 회원가입
- `POST /api/auth/login` - 로그인
- `GET /api/auth/me` - 현재 사용자 정보

### 게시글
- `GET /api/posts` - 모든 게시글 조회
- `GET /api/posts/{id}` - 특정 게시글 조회
- `POST /api/posts` - 게시글 작성 (인증 필요)
- `PUT /api/posts/{id}` - 게시글 수정 (인증 필요)
- `DELETE /api/posts/{id}` - 게시글 삭제 (인증 필요)
- `GET /api/posts/my` - 내 게시글 조회 (인증 필요)

## 페이지 구성

1. **홈** (`/`) - 게시글 목록 및 작성
2. **로그인** (`/login`) - 로그인/회원가입
3. **마이페이지** (`/mypage`) - 사용자 정보 및 내 게시글

## 환경 설정

### 데이터베이스 설정
`backend/src/main/resources/application.yml`에서 데이터베이스 연결 정보를 확인하세요:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/myappdb
    username: root
    password: dnjswns98!
```

### JWT 설정
JWT 시크릿 키와 만료 시간을 `application.yml`에서 설정할 수 있습니다:

```yaml
jwt:
  secret: your-secret-key
  expiration: 86400000 # 24시간
```

## 빌드

### 백엔드 빌드
```bash
cd backend
./gradlew build
```

### 프론트엔드 빌드
```bash
cd frontend
npm run build
```

## 라이센스

MIT License 