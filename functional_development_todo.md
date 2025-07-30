# 기능 개발 할 일 목록

이 문서는 RentHouse 프로젝트의 기능 개발에 대한 할 일 목록을 제공합니다. 현재 구현된 화면과 향후 구현되어야 할 화면을 중심으로 분석했습니다.

## 1. 회원 관리 (Member Module)

### 현재 화면:
- `member/join.ftl`: 회원 가입
- `admin/memberList.ftl`: 회원 목록 (모든 프로그램 사용자)
- `member/settings.ftl`: 회원 정보 설정 (개인 정보 수정, 비밀번호 변경)
- `member/profile.ftl`: 내 프로필 (회원 탈퇴)
- `admin/applyList.ftl`: 신규 회원 신청 목록

### 개발 할 일:
- **회원 상세 정보/수정 화면:** (완료 - `admin/memberList.ftl`에서 모달 창을 통해 상세 정보 조회 및 수정 기능 구현)
- **비밀번호 변경 기능:** (완료 - `member/settings.ftl`에서 구현)
- **회원 탈퇴 기능:** (완료 - `member/profile.ftl`에서 구현)
- **관리자용 회원 관리 기능 강화:** (완료)
    - `AdminRestController`를 통해 회원 정보 조회 (`/admin/member/{id}`), 업데이트 (`/admin/member/{id}` PUT), 승인 (`/admin/member/approve/{id}`), 거절 (`/admin/member/reject/{id}`), 비밀번호 초기화 (`/admin/member/reset-password/{id}`) 기능 구현 완료.
    - `admin/memberList.ftl`에서 모달 창을 통해 위 기능들을 사용할 수 있도록 구현 완료.

## 2. 부동산 관리 (Property Module)

### 현재 화면:
- `property/propertyList.ftl`: 부동산 목록
- `property/register.ftl`: 부동산 등록
- `property/unit/addRoom.ftl`: 유닛에 방 추가
- `property/unit/register.ftl`: 유닛 등록
- `property/detail.ftl`: 부동산 상세
- `property/unit/detail.ftl`: 유닛 상세

### 개발 할 일:
- **부동산 상세 정보/수정 화면:** (완료)
- **유닛 상세 정보/수정 화면:** (완료)
- **부동산/유닛 삭제 기능:** (미구현)
- **부동산 검색 및 필터링 기능:** (미구현)
- **이미지 업로드 및 관리:** (미구현)

## 3. 임대 관리 (Lease Module)

### 현재 화면:
- `admin/tenantsList.ftl`: 세입자 목록 (임대 계약자 목록)

### 개발 할 일:
- **임대 계약 목록 화면:** (미구현)
- **임대 계약 등록/수정 화면:** (미구현)
- **임대 계약 상세 화면:** (미구현)
- **임대 계약 상태 변경 기능:** (미구현)
- **임대료 계산 및 알림:** (미구현)

## 4. 청구 및 결제 관리 (Bill Module)

### 현재 화면:
- (명시된 청구/결제 관련 화면 없음)

### 개발 할 일:
- **청구서 목록 화면:** (미구현)
- **청구서 생성/수정 화면:** (미구현)
- **청구서 상세 화면:** (미구현)
- **결제 처리 기능:** (미구현)
- **자동 청구서 생성:** (미구현)

## 5. 관리자 기능 (Admin Module)

### 현재 화면:
- `admin/adminHome.ftl`: 관리자 홈
- `admin/applyList.ftl`: 신청 목록 (신규 회원 승인/거절)
- `admin/tenantsList.ftl`: 세입자 목록 (임대 계약자 목록)
- `admin/memberList.ftl`: 전체 회원 목록

### 개발 할 일:
- **관리자 대시보드 강화:** (미구현)
- **사용자 권한 관리:** (미구현)
- **시스템 설정 관리:** (미구현)

## 6. 공통 기능 및 개선

### 개발 할 일:
- **알림 시스템:** (미구현)
- **검색 기능 통합:** (미구현)
- **보고서 기능:** (미구현)
- **사용자 친화적인 URL:** (일부 RESTful API 구현됨)
- **성능 최적화:** (미구현)

## 출시 전 우선순위:
- **보안 강화:**
    - CSRF 보호 활성화 (미확인)
    - `/admin/**` 엔드포인트에 대한 적절한 역할 기반 접근 제어 구현 (일부 구현됨 - Spring Security 사용)
    - 기타 보안 취약점 점검 및 개선 (미확인)
