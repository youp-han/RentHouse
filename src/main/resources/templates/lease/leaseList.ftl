<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">임대차 계약 목록</h1>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">등록된 임대차 계약</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>임차인</th>
                        <th>임대 유닛</th>
                        <th>시작일</th>
                        <th>종료일</th>
                        <th>계약 종류</th>
                        <th>상태</th>
                        <th>액션</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if leases?? && (leases?size > 0)>
                        <#list leases as lease>
                            <tr>
                                <td>${lease.id!}</td>
                                <td>${lease.tenant.name!}</td>
                                <td>${lease.unit.property.name} - ${lease.unit.unitNumber}</td>
                                <td>${lease.startDate?string("yyyy-MM-dd")}</td>
                                <td>${lease.endDate?string("yyyy-MM-dd")}</td>
                                <td>${lease.leaseStatus.name()}</td>
                                <td>${lease.status.name()}</td>
                                <td>
                                    <a href="/leases/${lease.id}" class="btn btn-info btn-sm">상세</a>
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="8">임대차 계약이 없습니다.</td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <a href="/leases/register" class="btn btn-primary">임대차 계약 등록</a>

</div>
</#macro>
