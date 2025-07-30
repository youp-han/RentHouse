# 기능 개발 할 일 목록

이 문서는 RentHouse 프로젝트의 기능 개발에 대한 할 일 목록을 제공합니다. 현재 구현된 화면과 향후 구현되어야 할 화면을 중심으로 분석했습니다.

## 1. 회원 관리 (Member Module)

### 현재 화면:
- `member/join.ftl`: 회원 가입
- `member/memberList.ftl`: 회원 목록 (모든 프로그램 사용자)
- `member/settings.ftl`: 회원 정보 설정 (개인 정보 수정, 비밀번호 변경)
- `member/profile.ftl`: 내 프로필 (회원 탈퇴)

### 개발 할 일:
- **회원 상세 정보/수정 화면:** (완료 - `member/settings.ftl`에서 개인 정보 수정 및 비밀번호 변경 구현)
- **비밀번호 변경 기능:** (완료 - `member/settings.ftl`에서 구현)
- **회원 탈퇴 기능:** (완료 - `member/profile.ftl`에서 구현)
- **관리자용 회원 관리 기능 강화:** (완료 - `member/memberList.ftl` 및 `AdminRestController`를 통해 회원 정보 편집 모달 구현)
  - `MemberController`에 관리자용 회원 정보 조회 (`/admin/member/{id}`) 및 업데이트 (`/admin/member/{id}` PUT), 승인 (`/admin/member/approve/{id}`), 거절 (`/admin/member/reject/{id}`) 기능 구현 완료.

## 2. 부동산 관리 (Property Module)

### 현재 화면:
- `property/propertyList.ftl`: 부동산 목록
- `property/register.ftl`: 부동산 등록
- `property/unit/addRoom.ftl`: 유닛에 방 추가
- `property/unit/register.ftl`: 유닛 등록
- `property/detail.ftl`: 부동산 상세 (AdminController에서 `/admin/property/detail/{id}`로 연결)
- `property/unit/detail.ftl`: 유닛 상세 (AdminController에서 `/admin/property/unit/detail/{id}`로 연결)

### 개발 할 일:
- **부동산 상세 정보/수정 화면:**
  - `property/detail.ftl` (또는 `property/edit.ftl`) 구현 (완료 - `AdminController`에서 `property/detail`로 연결)
  - 부동산 정보 조회 및 수정 기능 (완료 - `PropertyRestController`에 `/api/properties/{id}` PUT 구현)
- **유닛 상세 정보/수정 화면:**
  - `property/unit/detail.ftl` (또는 `property/unit/edit.ftl`) 구현 (완료 - `AdminController`에서 `property/unit/detail`로 연결)
  - 유닛 정보 조회 및 수정 기능 (완료 - `PropertyRestController`에 `/api/units/{id}` PUT 구현)
- **부동산/유닛 삭제 기능:**
  - 부동산 및 유닛 삭제 기능 구현 (연관된 임대/청구 정보 처리 로직 포함) (완료 - `PropertyRestController`에 `/api/properties/{id}` DELETE 및 `/api/units/{id}` DELETE 구현)
- **부동산 검색 및 필터링 기능:**
  - 부동산 목록 페이지에 검색 및 필터링 옵션 추가 (미구현)
- **이미지 업로드 및 관리:**
  - 부동산 및 유닛 이미지 업로드, 표시, 관리 기능 구현 (미구현)

## 3. 임대 관리 (Lease Module)

### 현재 화면:
- `admin/tenantsList.ftl`: 세입자 목록 (임대 계약자 목록)

### 개발 할 일:
- **임대 계약 목록 화면:**
  - `lease/leaseList.ftl` 구현 (세입자 목록과 별개로 임대 계약 자체의 목록) (미구현)
  - 임대 계약 정보 조회 및 검색 기능 (미구현)
- **임대 계약 등록/수정 화면:**
  - `lease/register.ftl` (또는 `lease/edit.ftl`) 구현 (미구현 - `AdminRestController`에 `/admin/tenancy/save` API는 존재하나, 이를 위한 프론트엔드 화면은 명확히 보이지 않음)
  - 임대 계약 등록 및 수정 기능 (부동산, 유닛, 회원 연동) (등록 API는 존재)
- **임대 계약 상세 화면:**
  - `lease/detail.ftl` 구현 (미구현)
  - 임대 계약 상세 정보 및 관련 청구/결제 내역 표시 (미구현)
- **임대 계약 상태 변경 기능:**
  - 계약 활성화/비활성화, 만료 처리 등 상태 변경 기능 (미구현)
- **임대료 계산 및 알림:**
  - 임대료 자동 계산 및 납부 기한 알림 기능 (미구현)

## 4. 청구 및 결제 관리 (Bill Module)

### 현재 화면:
- (명시된 청구/결제 관련 화면 없음)

### 개발 할 일:
- **청구서 목록 화면:**
  - `bill/billList.ftl` 구현 (미구현)
  - 청구서 정보 조회 및 검색 기능 (미구현)
- **청구서 생성/수정 화면:**
  - `bill/create.ftl` (또는 `bill/edit.ftl`) 구현 (미구현)
  - 청구서 생성 및 수정 기능 (임대 계약, 유닛, 회원 연동) (미구현)
- **청구서 상세 화면:**
  - `bill/detail.ftl` 구현 (미구현)
  - 청구서 상세 정보 및 결제 상태 표시 (미구현)
- **결제 처리 기능:**
  - 결제 상태 업데이트, 결제 내역 기록 기능 (미구현)
- **자동 청구서 생성:**
  - 정기적인 임대료 청구서 자동 생성 기능 (미구현)

## 5. 관리자 기능 (Admin Module)

### 현재 화면:
- `admin/adminHome.ftl`: 관리자 홈
- `admin/applyList.ftl`: 신청 목록 (신규 회원 승인/거절)
- `admin/tenantsList.ftl`: 세입자 목록 (임대 계약자 목록)
- `tenant/tenantList.ftl`: 세입자 관리 (세입자 정보 CRUD)
- `tenant/register.ftl`: 세입자 등록
- `tenant/detail.ftl`: 세입자 상세/편집

### 개발 할 일:
- **관리자 대시보드 강화:**
  - 주요 통계, 최신 활동, 알림 등 대시보드 위젯 추가 (미구현)
- **신청 관리 기능 강화:** (완료 - `admin/applyList.ftl`에서 신규 회원 승인/거절 기능 구현)
- **세입자 관리 기능 강화:** (완료 - `tenant/tenantList.ftl`, `tenant/register.ftl`, `tenant/detail.ftl` 및 관련 컨트롤러/REST 컨트롤러 구현)
  - `TenantController`에 세입자 목록 (`/admin/tenants`), 상세 조회 (`/admin/tenants/{id}`), 생성 (`/admin/tenants` POST), 수정 (`/admin/tenants/{id}` PUT), 삭제 (`/admin/tenants/{id}` DELETE) 기능 구현 완료.
  - `TenantController`의 `convertToDto` 및 `convertToEntity` 메서드를 `EntityConverter`를 사용하도록 리팩토링 완료.
- **사용자 권한 관리:**
  - 사용자 역할(ADMIN, MEMBER 등)을 관리할 수 있는 화면 및 기능 (미구현)
- **시스템 설정 관리:**
  - 애플리케이션 전반의 설정을 관리할 수 있는 화면 및 기능 (미구현)

## 6. 공통 기능 및 개선

### 개발 할 일:
- **알림 시스템:**
  - 임대료 납부, 계약 만료, 새로운 신청 등 다양한 알림 기능 구현 (미구현)
- **검색 기능 통합:**
  - 전역 검색 기능 구현 (부동산, 회원, 임대 계약 등) (미구현)
- **보고서 기능:**
  - 월별/연간 임대 수익, 공실률 등 다양한 보고서 생성 기능 (미구현)
- **사용자 친화적인 URL:**
  - RESTful API 설계 및 URL 구조 개선 (일부 RESTful API 구현됨)
- **성능 최적화:**
  - 데이터 로딩 속도 개선, 쿼리 최적화 (미구현)

## 출시 전 우선순위:
- **보안 강화:**
  - CSRF 보호 활성화 (미확인)
  - `/admin/**` 엔드포인트에 대한 적절한 역할 기반 접근 제어 구현 (일부 구현됨 - Spring Security 사용)
  - 기타 보안 취약점 점검 및 개선 (미확인)