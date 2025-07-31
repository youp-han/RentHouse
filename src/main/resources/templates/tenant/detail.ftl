<#include "../../common/layout.ftl">

<@layout "세입자 상세 정보">

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">세입자 상세 정보</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">세입자 정보</h6>
        </div>
        <div class="card-body">
            <#if tenant??>
                <form id="tenantDetailForm">
                    <input type="hidden" id="tenantId" name="id" value="${tenant.id!}">
                    <div class="form-group">
                        <label for="name">이름</label>
                        <input type="text" class="form-control" id="name" name="name" value="${tenant.name!}" required>
                    </div>
                    <div class="form-group">
                        <label for="email">이메일</label>
                        <input type="email" class="form-control" id="email" name="email" value="${tenant.email!}" required>
                    </div>
                    <div class="form-group">
                        <label for="phone">전화번호</label>
                        <input type="text" class="form-control" id="phone" name="phone" value="${tenant.phone!}">
                    </div>
                    <div class="form-group">
                        <label for="socialNo">사회보장번호</label>
                        <input type="text" class="form-control" id="socialNo" name="socialNo" value="${tenant.socialNo!}">
                    </div>
                    <div class="form-group">
                        <label for="currentAddress">현재 주소</label>
                        <input type="text" class="form-control" id="currentAddress" name="currentAddress" value="${tenant.currentAddress!}">
                    </div>
                    <button type="submit" class="btn btn-primary">정보 업데이트</button>
                    <button type="button" class="btn btn-danger" id="deleteTenantBtn" data-tenant-id="${tenant.id!}">세입자 삭제</button>
                </form>
            <#else>
                <p>세입자 정보를 찾을 수 없습니다.</p>
            </#if>
        </div>
    </div>

</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('tenantDetailForm').addEventListener('submit', function(event) {
            event.preventDefault();

            const tenantId = document.getElementById('tenantId').value;
            const formData = {
                id: tenantId,
                name: document.getElementById('name').value,
                email: document.getElementById('email').value,
                phone: document.getElementById('phone').value,
                socialNo: document.getElementById('socialNo').value,
                currentAddress: document.getElementById('currentAddress').value
            };

            fetch(`/api/tenants/${tenantId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                if (data.id) {
                    alert('세입자 정보가 성공적으로 업데이트되었습니다.');
                    // Optionally redirect or update UI
                } else {
                    alert('세입자 정보 업데이트 실패.');
                }
            })
            .catch(error => console.error('Error updating tenant:', error));
        });

        document.getElementById('deleteTenantBtn').addEventListener('click', function() {
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
                        window.location.href = '/tenants'; // Redirect to tenant list after deletion
                    } else {
                        alert('세입자 삭제 실패.');
                    }
                })
                .catch(error => console.error('Error deleting tenant:', error));
            }
        });
    });
</script>

</@layout>