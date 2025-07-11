<<<Cursor AI로 게시판만들기>>>

처음으로 커서ai 무료버전을 써 봤으나 생각보다 잘 만들어서 놀랐다
한번에 뚝딱 만들어주긴 하나
프레임워크나 라이브러리, 빌드도구 등의 버전호환오류가 너무 많이 생겨서 다 수정해 달라고 했다

그리고 스프링 버전과 마이바티스 버전이 안맞는다고 지멋대로 마이바티스 삭제해버리고 jpa를 썼다

커서를 VS코드에서 실행해서 로컬테스트나 깃에 푸쉬할 때는 인텔리제이에서 열어서 했는데
인텔리제이 JDK설정 충돌문제가 발생하면
얘는 그냥 그레이들 빌드설정파일의 자바버전을 바꿔버린다
그럼 다시 인텔리제이 가서 JDK 버전 설정 바꾸면 또 안되고(에효..)

문제상황을 제대로 분석을 하려하지 않고 그냥 막무가내로 코드를 수정해서 억지로 실행되게 하는걸 목표로 삼은 듯 하다
지가 문제를 대충 판단하고 지 스스로 해결책을 내놓는데 
그냥 "언 발에 오줌 누기" 식이다

무료버전이라 그런건지, 내가 사용법을 모르는건지는 모르겠지만

그래도 잘 활용하면 좋은 도구가 될 것 같다


Cursor AI에게 준 메세지
***********************************************
간단한 게시판 만들거야

페이지는 단 세개

홈
로그인 페이지
마이페이지

백엔드는 자바 jdk버전 corretto-17 Amazon Corretto 17.0.13
프론트는 리엑트 버전 18.2.0

RESTful API로 만들어줘

인증방식: JWT 

마이바티스, MySQL쓸거야
빌드도구는 그레이들

host: localhost
port: 3306
database: myappdb
username: root
password: XXXXXXXXX

***********************************************



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
