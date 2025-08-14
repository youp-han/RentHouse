# 기능 개발 할 일 목록

이 문서는 RentHouse 프로젝트의 기능 개발에 대한 할 일 목록을 제공합니다. 현재 구현된 화면과 향후 구현되어야 할 화면을 중심으로 분석했습니다.

## 1. 회원 관리 (Member Module)

### 현재 화면:
- `member/join.ftl`: 회원 가입
- `admin/memberList.ftl`: 회원 목록 (모든 프로그램 사용자)
- `member/settings.ftl`: 회원 정보 설정 (개인 정보 수정, 비밀번호 변경)
- `member/profile.ftl`: 내 프로필 (회원 탈퇴)

### 개발 할 일:
- **회원 상세 정보/수정 화면:** (완료 - `admin/memberList.ftl`에서 모달 창을 통해 상세 정보 조회 및 수정 기능 구현)
- **비밀번호 변경 기능:** (완료 - `member/settings.ftl`에서 구현)
- **회원 탈퇴 기능:** (완료 - `member/profile.ftl`에서 구현)
- **관리자용 회원 관리 기능 강화:** (완료)
    - `MemberController` 내의 RESTful API 엔드포인트 (`/member/{id}` GET/PUT, `/member/approve/{id}` POST, `/member/reject/{id}` POST, `/member/reset-password/{id}` POST)를 통해 회원 정보 조회, 업데이트, 승인, 거절, 비밀번호 초기화 기능 구현 완료.
    - `admin/memberList.ftl`에서 모달 창을 통해 위 기능들을 사용할 수 있도록 구현 완료.
- **UI 개선:** (완료)
    - `admin/memberList.ftl` 화면의 데이터 테이블 하단에 '회원 등록' 버튼을 추가하여 사용자 편의성을 개선했습니다.

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
    - 데이터 테이블 하단에 '부동산 등록' 버튼을 추가하여 신규 등록 접근성을 높였습니다.
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
- `lease/leaseList.ftl`: 임대 계약 목록
- `lease/register.ftl`: 임대 계약 등록
- `lease/edit.ftl`: 임대 계약 수정

### 개발 할 일:
- **UI 개선:** (완료)
    - `admin/tenantsList.ftl` 화면의 데이터 테이블 하단에 '세입자 등록' 버튼을 추가했습니다.
- **임대 계약 목록 화면:** (완료 - `lease/leaseList.ftl` 구현 및 상세/수정/삭제 기능 연결)
- **임대 계약 등록 화면:** (완료 - `lease/register.ftl` 구현 및 임차인 신규 등록 연동)
- **임대 계약 수정 화면:** (완료 - `lease/edit.ftl` 구현 및 상세/수정/삭제 기능 구현)
- **임대 계약 상태 변경 기능:** (완료 - `lease/edit.ftl`에서 상태 변경 가능)
- **임대료 계산 및 알림:** (미구현)

## 4. 청구 및 결제 관리 (Bill Module)

### 현재 화면:
- `billing/billingList.ftl`: 청구서 목록
- `bill/billList.ftl`: 청구 항목 관리 목록

### 개발 할 일:
- **청구서 목록 화면:** (완료 - `billing/billingList.ftl` 및 `BillingController` 구현됨)
- **청구서 생성/수정 화면:** (완료 - `BillService`에 관련 메소드 존재, `billList.ftl`에 수정 링크 있음)
- **청구서 상세 화면:** (미구현)
- **결제 처리 기능:** (일부 구현됨 - `Billing` 엔티티에 `paid` 필드 존재)
- **자동 청구서 생성:** (백엔드 로직 존재하나 `BillingServiceImpl`에서 `TODO` 상태)

## 5. 관리자 기능 (Admin Module)

### 현재 화면:
- `admin/tenantsList.ftl`: 세입자 목록 (임대 계약자 목록)
- `admin/memberList.ftl`: 전체 회원 목록
- `admin/dashboard.ftl`: 관리자 대시보드

### 개발 할 일:
- **관리자 대시보드 강화:** (진행 중)
  - **주요 통계 위젯:** (완료)
      - 총 부동산/세대 수 (완료)
      - 공실률 (비율 및 숫자) (완료)
      - 연체된 임대료 (총액 및 건수) (완료)
      - 계약 만료 예정 건수 (30일 내) (완료)
      - 월별 수입 현황 (차트) (완료)
      - 유지보수 요청 현황 (상태별 건수) (미구현)
  - **최신 활동 피드:** (완료)
      - 신규 계약, 임대료 납부, 신규 임차인 등록 등 주요 이벤트 목록
  - **알림 센터:** (미구현)
      - 임대료 연체, 계약 만료 임박, 신규 유지보수 요청 등 즉시 조치 필요한 항목
  - **지도 기반 부동산 현황:** (미구현)
      - 지도에 관리 부동산 위치 표시 및 요약 정보 제공
  - **빠른 실행 버튼:** (완료)
      - 자주 사용하는 기능 (신규 계약, 임차인 추가 등) 바로가기
- **세입자 관리 기능 강화:** (완료 - `TenantController` 및 관련 REST 컨트롤러 구현)
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
- **코드 정리:** (완료)
    - 사용하지 않는 `.ftl` 파일(`common/layout-sample.ftl`, `sample.ftl`, `tenant/detail.ftl`, `tenant/register.ftl`, `tenant/tenantList.ftl`, `admin/adminHome.ftl`, `admin/applyList.ftl`)을 삭제하여 프로젝트 구조를 정리했습니다.

## 7. API 개발

### 개발 할 일:
- **데이터 일괄 등록 API:** (완료)
    - 여러 개의 부동산, 유닛, 회원, 임차인 정보를 한 번의 요청으로 생성할 수 있는 API 엔드포인트를 추가했습니다. (`/api/properties`, `/api/units`, `/api/members`, `/api/tenants`)
    - `api-data.http` 파일에 새로운 API를 테스트할 수 있는 샘플 요청을 추가했습니다.

## 8. 아키텍처 및 코드 품질 개선

### 개발 할 일:
- **서비스/구현체 패키지 구조 재정비:** (완료)
    - 인터페이스는 최상위 `renthouse/service/` 패키지에, 구현체는 모듈별 `renthouse/module/{모듈명}/service/` 패키지에 위치하도록 변경.
- **컴파일 및 런타임 오류 수정:** (완료)
    - `ActivityLog` 관련 필드명 불일치 오류 수정.
    - `BillingService` 메소드 호출 및 임포트 경로 오류 수정.

## 출시 전 우선순위:
- **보안 강화:**
    - CSRF 보호 활성화 (미확인)
    - `/admin/**` 엔드포인트에 대한 적절한 역할 기반 접근 제어 구현 (일부 구현됨 - Spring Security 사용)
    - 기타 보안 취약점 점검 및 개선 (미확인)