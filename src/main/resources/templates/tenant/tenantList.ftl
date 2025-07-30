<#include "../../common/layout.ftl">

<@layout "세입자 관리">

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">세입자 관리</h1>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">등록된 세입자</h6>
        </div>
        <div class="card-body">
            <a href="/admin/tenants/register" class="btn btn-primary btn-sm mb-3">새 세입자 등록</a>
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>전화번호</th>
                        <th>사회보장번호</th>
                        <th>현재 주소</th>
                        <th>가입일</th>
                        <th>액션</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if tenants??>
                        <#list tenants as tenant>
                            <tr>
                                <td>${tenant.id!}</td>
                                <td>${tenant.name!}</td>
                                <td>${tenant.email!}</td>
                                <td>${tenant.phone!}</td>
                                <td>${tenant.socialNo!}</td>
                                <td>${tenant.currentAddress!}</td>
                                <td>${tenant.createdDate?string("yyyy-MM-dd HH:mm")!}</td>
                                <td>
                                    <a href="/admin/tenants/edit/${tenant.id!}" class="btn btn-info btn-sm">편집</a>
                                    <button class="btn btn-danger btn-sm delete-tenant-btn" data-tenant-id="${tenant.id!}">삭제</button>
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="8">등록된 세입자가 없습니다.</td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.querySelectorAll('.delete-tenant-btn').forEach(button => {
            button.addEventListener('click', function() {
                const tenantId = this.dataset.tenantId;
                if (confirm('이 세입자를 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
                    fetch(`/api/tenants/${tenantId}`, {
                        method: 'DELETE',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(response => {
                        if (response.ok) {
                            alert('세입자가 성공적으로 삭제되었습니다.');
                            location.reload();
                        } else {
                            alert('세입자 삭제 실패.');
                        }
                    })
                    .catch(error => console.error('Error deleting tenant:', error));
                }
            });
        });
    });
</script>

</@layout>