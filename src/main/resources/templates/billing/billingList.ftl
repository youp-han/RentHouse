<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">청구서 목록</h1>
    <p class="mb-4">모든 임대차 계약에 대한 청구서 내역입니다.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">청구서</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>임대 정보</th>
                            <th>카테고리</th>
                            <th>청구 기간</th>
                            <th>금액</th>
                            <th>발행일</th>
                            <th>납부 기한</th>
                            <th>납부 여부</th>
                            <th>관리</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list bills! as bill>
                        <tr>
                            <td>${bill.propertyName} ${bill.unitNumber} (${bill.tenantName})</td>
                            <td>${bill.category.name()}</td>
                            <td>${bill.period}</td>
                            <td>${bill.amount?string.currency}</td>
                            <td>${bill.issueDate}</td>
                            <td>${bill.dueDate}</td>
                            <td><#if bill.paid>납부 완료<#else>미납</#if></td>
                            <td>
                                <a href="/bills/edit/${bill.id}" class="btn btn-primary btn-sm">수정</a>
                                <a href="/bills/delete/${bill.id}" class="btn btn-danger btn-sm">삭제</a>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
            
        </div>
    </div>

</div>
</#macro>
