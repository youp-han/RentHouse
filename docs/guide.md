# RentHouse 프로젝트 가이드

이 문서는 RentHouse 프로젝트의 주요 화면과 기능에 대한 설명을 제공합니다.

## 1. 부동산 목록 (Property List) 화면 설명

이 화면은 시스템에 등록된 모든 부동산 목록을 보여주고, 각 부동산의 상세 정보를 확인하거나 신규 부동산을 등록할 수 있는 시작점 역할을 합니다.

### 1.1. 주요 기능 및 사용자 경험

*   **부동산 목록 표시:** 각 부동산의 이름, 주소, 유형 등 핵심 정보가 테이블 형태로 표시됩니다.
*   **데이터 테이블 기능:** `DataTables` 라이브러리가 적용되어 있어, 목록 검색, 정렬, 페이지네이션 등의 기능을 기본적으로 제공합니다.
*   **신규 부동산 등록:** 테이블 하단의 '부동산 등록' 버튼을 클릭하여 새로운 부동산 정보를 등록하는 페이지로 이동할 수 있습니다.
*   **상세 정보 조회:** 각 부동산 행의 '상세 보기' 버튼을 클릭하면, 해당 부동산의 상세 정보를 보여주는 페이지로 이동합니다.

### 1.2. 기술적 구현 (간략히)

*   **백엔드 (Java Spring Boot):**
    *   `PropertyController`의 `listProperties()` 메소드가 모든 부동산 목록 데이터를 조회하여 `property/propertyList.ftl` 템플릿으로 전달합니다.
*   **프론트엔드 (FreeMarker, HTML, CSS, JavaScript):**
    *   `property/propertyList.ftl` 템플릿은 `common/layout.ftl`을 포함하여 전체적인 레이아웃을 구성합니다.
    *   테이블은 FreeMarker의 반복문(`<#list>`)을 사용하여 `properties` 모델 데이터를 기반으로 동적으로 생성됩니다.
    *   '상세 보기' 버튼은 `/property/detail/{id}` 형태의 URL로, '부동산 등록' 버튼은 `/property/register` URL로 연결됩니다.

### 1.3. 사용자 흐름

1.  사용자가 로그인 후 사이드바 메뉴에서 "Property" 또는 관련 메뉴를 클릭합니다.
2.  `PropertyController`의 `listProperties()` 메소드가 호출되어 모든 부동산 목록을 가져옵니다.
3.  `property/propertyList.ftl` 페이지가 렌더링되어 부동산 목록 테이블을 표시합니다.
4.  사용자가 신규 부동산을 등록하려면 '부동산 등록' 버튼을 클릭하여 등록 페이지로 이동합니다.
5.  사용자가 특정 부동산의 상세 정보를 보기 위해 '상세 보기' 버튼을 클릭합니다.
6.  브라우저는 해당 부동산의 ID가 포함된 URL(`/property/detail/{id}`)로 이동하여 상세 정보 페이지를 요청합니다.

## 2. 세입자 목록 (Tenants List) 화면 설명

이 화면은 시스템에 등록된 모든 세입자의 목록을 관리자가 한눈에 확인하고, 각 세입자의 상세 정보를 조회하거나 수정, 신규 등록할 수 있도록 제공하는 관리자 페이지입니다.

### 2.1. 주요 기능 및 사용자 경험

*   **세입자 목록 표시:** 세입자의 ID, 이름, 전화번호, 이메일, 주민등록번호, 현재 주소 등의 핵심 정보가 테이블 형태로 표시됩니다.
*   **데이터 테이블 기능:** `DataTables` 라이브러리가 적용되어 있어, 목록 검색, 정렬, 페이지네이션 등의 기능을 기본적으로 제공합니다.
*   **신규 세입자 등록:** 테이블 하단의 '세입자 등록' 버튼을 클릭하여 신규 세입자 등록 페이지로 이동할 수 있습니다.
*   **상세 정보 조회 및 수정 모달:** 각 세입자 행을 클릭하거나 "상세" 버튼을 클릭하면, 해당 세입자의 상세 정보를 보여주고 수정할 수 있는 모달 창이 나타납니다. 이 모달에서 정보를 변경하고 "변경 사항 저장" 버튼을 누르면 데이터가 업데이트됩니다.

### 2.2. 기술적 구현 (간략히)

*   **백엔드 (Java Spring Boot):**
    *   `TenantController`의 `getAllTenants()` 메서드가 세입자 목록 데이터를 조회하여 `admin/tenantsList.ftl` 템플릿으로 전달합니다.
    *   `TenantController`는 또한 세입자 상세 정보 조회(`GET /admin/tenants/{id}`), 생성(`POST /admin/tenants`), 수정(`PUT /admin/tenants/{id}`), 삭제(`DELETE /admin/tenants/{id}`)를 위한 RESTful API 엔드포인트를 제공합니다.
    *   `EntityConverter`를 사용하여 `Tenant` 엔티티와 `TenantDto` 간의 데이터 변환을 처리하여 코드의 재사용성과 유지보수성을 높였습니다.
*   **프론트엔드 (FreeMarker, HTML, CSS, JavaScript):**
    *   `tenant/tenantsList.ftl` 템플릿은 `common/layout.ftl`을 포함하여 전체적인 레이아웃을 구성합니다.
    *   테이블은 FreeMarker의 반복문(`list`)을 사용하여 `tenants` 모델 데이터를 기반으로 동적으로 생성됩니다.
    *   JavaScript (jQuery 및 Fetch API)를 사용하여 세입자 상세 정보 모달을 제어하고, 모달 내에서 세입자 정보를 조회하거나 수정할 때 백엔드 API와 비동기적으로 통신합니다.

### 2.3. 사용자 흐름

1.  관리자가 로그인 후 사이드바 메뉴에서 "Tenants"를 클릭합니다.
2.  `TenantController`의 `getAllTenants()` 메서드가 호출되어 세입자 목록을 가져옵니다.
3.  `admin/tenantsList.ftl` 페이지가 렌더링되어 세입자 목록 테이블을 표시합니다.
4.  관리자가 신규 세입자를 등록하려면 '세입자 등록' 버튼을 클릭합니다.
5.  관리자가 특정 세입자의 정보를 확인하거나 수정하기 위해 해당 테이블 행 또는 "상세" 버튼을 클릭합니다.
6.  JavaScript 코드가 실행되어 해당 세입자의 ID를 기반으로 `GET /admin/tenants/{id}` API를 호출하여 상세 정보를 가져옵니다.
7.  가져온 상세 정보가 모달 창에 채워지고 모달이 표시됩니다.
8.  관리자가 정보를 수정한 후 "변경 사항 저장" 버튼을 클릭하면, JavaScript 코드가 `PUT /admin/tenants/{id}` API를 호출하여 변경된 데이터를 백엔드로 전송하고, 성공 시 페이지를 새로고침하여 업데이트된 목록을 보여줍니다.

## 3. 임대차 계약 관리 (Lease Module) 화면 설명

이 화면은 시스템에 등록된 모든 임대차 계약을 관리하고, 신규 계약 등록, 기존 계약 수정 및 삭제를 수행할 수 있는 페이지입니다.

### 3.1. 주요 기능 및 사용자 경험

*   **임대차 계약 목록 표시:** 각 임대차 계약의 ID, 임차인, 임대 유닛, 시작일, 종료일, 계약 종류, 상태 등의 핵심 정보가 테이블 형태로 표시됩니다.
*   **데이터 테이블 기능:** `DataTables` 라이브러리가 적용되어 있어, 목록 검색, 정렬, 페이지네이션 등의 기능을 기본적으로 제공합니다.
*   **신규 임대차 계약 등록:** 테이블 하단의 '임대차 계약 등록' 버튼을 클릭하여 새로운 임대차 계약 정보를 등록하는 페이지로 이동할 수 있습니다.
*   **상세 정보 조회 및 수정:** 각 임대차 계약 행의 '상세' 버튼을 클릭하면, 해당 임대차 계약의 상세 정보를 보여주고 수정할 수 있는 `lease/edit.ftl` 페이지로 이동합니다. 이 페이지에서 정보를 변경하고 "저장" 버튼을 누르면 데이터가 업데이트됩니다.
*   **임대차 계약 삭제:** `lease/edit.ftl` 페이지에서 "삭제" 버튼을 통해 해당 임대차 계약을 삭제할 수 있습니다.
*   **임차인 신규 등록 연동:** 임대차 계약 등록 시 임차인 선택 드롭다운에서 '-- 신규 임차인 등록 --' 옵션을 선택하면, 임차인 등록 페이지로 이동하여 신규 임차인을 등록한 후 다시 임대차 계약 등록 페이지로 돌아올 수 있습니다.

### 3.2. 기술적 구현 (간략히)

*   **백엔드 (Java Spring Boot):**
    *   `LeaseController`의 `getAllLeases()` 메소드가 모든 임대차 계약 목록 데이터를 조회하여 `lease/leaseList.ftl` 템플릿으로 전달합니다.
    *   `LeaseController`는 임대차 계약 상세 정보 조회(`GET /leases/{id}`), 생성(`POST /leases`), 수정(`PUT /leases/{id}`), 삭제(`DELETE /leases/{id}`)를 위한 RESTful API 엔드포인트를 제공합니다.
    *   `LeaseController`의 `editLeaseForm()` 메소드는 `GET /leases/edit/{id}` 요청을 처리하여 `lease/edit.ftl` 템플릿을 렌더링하고, 해당 임대차 계약의 상세 정보를 모델에 추가합니다.
    *   `LeaseService`는 임대차 계약의 비즈니스 로직을 처리하며, `LeaseStatus` enum을 사용하여 계약 상태를 관리합니다. 신규 계약 등록 시 `leaseStatus`는 기본적으로 `PENDING`으로 설정됩니다.
    *   `EntityConverter`는 `Lease` 엔티티와 `LeaseDto` 간의 데이터 변환을 처리하며, `LeaseDto`에는 `tenantName`, `propertyName`, `unitNumber` 등 화면 표시에 필요한 정보가 포함됩니다.
    *   `TenantController`의 `registerTenantForm()` 메소드는 `redirectUrl` 파라미터를 받아 임차인 등록 후 특정 URL로 리다이렉트할 수 있도록 지원합니다.
*   **프론트엔드 (FreeMarker, HTML, CSS, JavaScript):**
    *   `lease/leaseList.ftl`, `lease/register.ftl`, `lease/edit.ftl` 템플릿은 `common/layout.ftl`을 포함하여 전체적인 레이아웃을 구성합니다.
    *   테이블은 FreeMarker의 반복문(`<#list>`)을 사용하여 모델 데이터를 기반으로 동적으로 생성됩니다.
    *   JavaScript (jQuery 및 Fetch API)를 사용하여 다음을 수행합니다.
        *   `lease/leaseList.ftl`에서 '상세' 버튼 클릭 시 `lease/edit.ftl` 페이지로 이동합니다.
        *   `lease/edit.ftl`에서 페이지 로드 시 `GET /leases/{id}` API를 호출하여 상세 정보를 가져와 폼을 채웁니다.
        *   `lease/edit.ftl`에서 "저장" 버튼 클릭 시 `PUT /leases/{id}` API를 호출하여 변경된 데이터를 백엔드로 전송하고, 성공 시 목록 화면으로 리다이렉트합니다.
        *   `lease/edit.ftl`에서 "삭제" 버튼 클릭 시 `DELETE /leases/{id}` API를 호출하여 임대차 계약을 삭제하고, 성공 시 목록 화면으로 리다이렉트합니다.
        *   `lease/register.ftl`에서 임차인 선택 드롭다운의 '-- 신규 임차인 등록 --' 옵션 선택 시 `redirectUrl` 파라미터와 함께 `tenant/register` 페이지로 이동합니다.

### 3.3. 사용자 흐름

1.  사용자가 로그인 후 사이드바 메뉴에서 "Leases" 또는 관련 메뉴를 클릭합니다.
2.  `LeaseController`의 `getAllLeases()` 메소드가 호출되어 모든 임대차 계약 목록을 가져옵니다.
3.  `lease/leaseList.ftl` 페이지가 렌더링되어 임대차 계약 목록 테이블을 표시합니다.
4.  사용자가 신규 임대차 계약을 등록하려면 '임대차 계약 등록' 버튼을 클릭하여 `lease/register.ftl` 페이지로 이동합니다.
5.  `lease/register.ftl`에서 임차인 선택 시 '-- 신규 임차인 등록 --'을 선택하면, 임차인 등록 페이지로 이동하여 신규 임차인을 등록합니다. 등록 완료 후 자동으로 `lease/register.ftl`로 돌아옵니다.
6.  `lease/register.ftl`에서 모든 정보를 입력하고 '등록' 버튼을 클릭하면 `POST /leases` API를 통해 임대차 계약이 등록되고 목록 화면으로 돌아옵니다.
7.  사용자가 특정 임대차 계약의 정보를 확인하거나 수정하기 위해 `lease/leaseList.ftl`에서 해당 행의 '상세' 버튼을 클릭합니다.
8.  브라우저는 해당 임대차 계약의 ID가 포함된 URL(`/leases/edit/{id}`)로 이동하여 `lease/edit.ftl` 페이지를 요청합니다.
9.  `lease/edit.ftl` 페이지 로드 시 JavaScript가 `GET /leases/{id}` API를 호출하여 상세 정보를 가져와 폼을 채웁니다.
10. 사용자가 정보를 수정한 후 '저장' 버튼을 클릭하면, JavaScript 코드가 `PUT /leases/{id}` API를 호출하여 변경된 데이터를 백엔드로 전송하고, 성공 시 목록 화면으로 리다이렉트합니다.
11. 사용자가 임대차 계약을 삭제하려면 '삭제' 버튼을 클릭합니다. 확인 후 JavaScript 코드가 `DELETE /leases/{id}` API를 호출하여 임대차 계약을 삭제하고, 성공 시 목록 화면으로 리다이렉트합니다.

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
- `admin/tenantsList.ftl`: 세입자 목록 (임대 계약자 목록)
- `admin/memberList.ftl`: 전체 회원 목록

### 개발 할 일:
- **관리자 대시보드 강화:**
  - 주요 통계, 최신 활동, 알림 등 대시보드 위젯 추가 (미구현)
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

## 출시 전 우선순위:
- **보안 강화:**
    - CSRF 보호 활성화 (미확인)
    - `/admin/**` 엔드포인트에 대한 적절한 역할 기반 접근 제어 구현 (일부 구현됨 - Spring Security 사용)
    - 기타 보안 취약점 점검 및 개선 (미확인)