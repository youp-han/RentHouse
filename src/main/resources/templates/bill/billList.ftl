<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">청구 항목 관리</h1>
    <p class="mb-4">청구 항목의 종류와 표준 금액을 관리합니다.</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">청구 항목</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>이름</th>
                            <th>카테고리</th>
                            <th>금액</th>
                            <th>설명</th>
                            <th>관리</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list bills as bill>
                        <tr>
                            <td>${bill.name}</td>
                            <td>${bill.category.name()}</td>
                            <td>${bill.amount?string.currency}</td>
                            <td>${bill.description}</td>
                            <td>
                                <a href="/bills/edit/${bill.id}" class="btn btn-primary btn-sm">수정</a>
                                
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
            <a href="/bills/register" class="btn btn-primary">신규 항목 등록</a>
        </div>
    </div>

</div>
</#macro>
