# RentHouse - 부동산 임대 관리 시스템

RentHouse는 부동산 임대 관리를 위한 웹 애플리케이션입니다. 임대인, 임차인, 관리자 등 다양한 사용자가 시스템을 통해 부동산, 유닛, 임대 계약, 결제 등을 효율적으로 관리할 수 있습니다.

## 주요 기능

- **회원 관리:** 사용자 등록, 로그인, 정보 수정, 회원 등급(관리자, 임대인, 임차인) 관리
- **부동산 관리:** 부동산 정보(주소, 유형, 층수 등) 등록, 수정, 삭제 및 유닛(세대) 관리
- **임대 관리:** 임대 계약 등록, 수정, 삭제 및 계약 상태 관리
- **청구 및 결제 관리:** 임대료 청구서 자동 생성, 결제 내역 관리
- **관리자 기능:** 전체 시스템 현황 모니터링, 사용자 관리, 시스템 설정

## 최근 업데이트 내용 (2025-08-05)

- **API 기능 추가:**
    - 여러 개의 부동산, 유닛, 회원, 임차인 정보를 한 번의 요청으로 생성할 수 있는 API 엔드포인트를 추가했습니다. (`/api/properties`, `/api/units`, `/api/members`, `/api/tenants`)
    - `api-data.http` 파일에 새로운 API를 테스트할 수 있는 샘플 요청을 추가했습니다.
- **보안 설정 변경:**
    - API 엔드포인트 (`/api/**`)에 대한 접근을 허용하도록 Spring Security 설정을 변경했습니다.

## 기술 스택

- **Backend:** Java, Spring Boot, Spring Security
- **Frontend:** HTML, CSS, JavaScript, Bootstrap, FreeMarker, SB Admin 2
- **Database:** H2 (개발용), PostgreSQL 또는 MySQL (운영용 권장)
- **Build Tool:** Maven

## 시작하기

### 요구 사항

- Java 17 또는 그 이상
- Maven 3.2 또는 그 이상

### 실행 방법

1.  **저장소 복제:**
    ```bash
    git clone https://github.com/your-username/renthouse.git
    cd renthouse
    ```

2.  **애플리케이션 빌드:**
    ```bash
    ./mvnw clean install
    ```

3.  **애플리케이션 실행:**
    ```bash
    java -jar target/renthouse-0.0.1-SNAPSHOT.jar
    ```
    또는 Maven 플러그인을 사용하여 실행할 수 있습니다:
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **애플리케이션 접속:**
    웹 브라우저에서 `http://localhost:8080`으로 접속합니다.

## 라이선스

이 프로젝트는 [MIT 라이선스](LICENSE)에 따라 배포됩니다.
