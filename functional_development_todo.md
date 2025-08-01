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
    - `MemberController` 내의 RESTful API 엔드포인트 (`/member/{id}` GET/PUT, `/member/approve/{id}` POST, `/member/reject/{id}` POST, `/member/reset-password/{id}` POST)를 통해 회원 정보 조회, 업데이트, 승인, 거절, 비밀번호 초기화 기능 구현 완료.
    - `admin/memberList.ftl`에서 모달 창을 통해 위 기능들을 사용할 수 있도록 구현 완료.

## 2. 부동산 관리 (Property Module)

### 현재 화면:
- `property/propertyList.ftl`: 부동산 목록
- `property/register.ftl`: 부동산 등록 (Property 엔티티의 모든 필드 포함)
- `property/unit/register.ftl`: 유닛 등록 (Unit 엔티티의 모든 필드 포함)
- `property/detail.ftl`: 부동산 상세
- `property/unit/detail.ftl`: 유닛 상세

### 개발 할 일:
- **부동산 등록 화면 개선:** (완료)
    - `property/register.ftl`에서 유형(enums/PropertyType) 리스트를 한글로 번역하여 표시하고, 총 층수 선택 시 '직접 입력' 옵션을 추가하여 동적으로 입력 필드를 표시하도록 개선했습니다.
    - 주소 저장을 위해 `roadAddress`, `detailAddress`, `zipCode`를 조합하여 `address` 필드에 저장하도록 `PropertyController`를 수정했습니다.
    - `WKUp_VILLA` 유형일 때만 `totalUnits`를 입력받고, 나머지 유형은 1로 고정하도록 기능을 추가했습니다.
- **부동산 목록 화면 개선:** (완료)
    - `propertyList.ftl`에 각 부동산의 상세 정보를 볼 수 있는 '상세 보기' 버튼을 추가했습니다.
- **부동산 상세 정보/수정 화면:** (완료)
    - `property/detail.ftl`에서 부동산 정보를 수정하고 삭제할 수 있는 기능을 추가했습니다.
    - 주소는 변경할 수 없도록 `readonly` 속성을 추가하고, 업데이트 로직에서 제외했습니다.
    - 해당 부동산에 속한 Unit 정보를 함께 표시하고, 팝업을 통해 Unit을 추가/수정/삭제할 수 있도록 개선했습니다.
- **유닛 상세 정보/수정 화면:** (완료 - `property/detail.ftl`의 팝업에서 처리)
- **부동산/유닛 삭제 기능:** (완료 - `property/detail.ftl`에서 구현)
- **부동산 검색 및 필터링 기능:** (미구현)
- **이미지 업로드 및 관리:** (미구현)

## 3. 임대 관리 (Lease Module)

### 현재 화면:
- `admin/tenantsList.ftl`: 세입자 목록 (임대 계약자 목록)

### 개발 할 일:
- **임대 계약 목록 화면:** (백엔드 로직 구현 완료 - `LeaseServiceImpl.getAllLeaseDtos()`, 프론트엔드 화면 미구현)
- **임대 계약 등록/수정 화면:** (백엔드 로직 구현 완료 - `LeaseServiceImpl.registerLease()`, 프론트엔드 화면 미구현)
- **임대 계약 상세 화면:** (미구현)
- **임대 계약 상태 변경 기능:** (미구현)
- **임대료 계산 및 알림:** (미구현)

## 4. 청구 및 결제 관리 (Bill Module)

### 현재 화면:
- (명시된 청구/결제 관련 화면 없음)

### 개발 할 일:
- **청구서 목록 화면:** (백엔드 로직 미구현)
- **청구서 생성/수정 화면:** (백엔드 로직 미구현)
- **청구서 상세 화면:** (백엔드 로직 미구현)
- **결제 처리 기능:** (백엔드 로직 미구현)
- **자동 청구서 생성:** (백엔드 로직 미구현)

## 5. 관리자 기능 (Admin Module)

### 현재 화면:
- `admin/adminHome.ftl`: 관리자 홈
- `admin/applyList.ftl`: 신청 목록 (신규 회원 승인/거절)
- `admin/tenantsList.ftl`: 세입자 목록 (임대 계약자 목록)
- `admin/memberList.ftl`: 전체 회원 목록
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
