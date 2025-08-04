<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">세입자 등록</h1>

    <!-- Tenant Registration Form -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">신규 세입자 정보</h6>
        </div>
        <div class="card-body">
            <form id="registerTenantForm">
                <div class="form-group">
                    <label for="tenantName">이름</label>
                    <input type="text" class="form-control" id="tenantName" name="name" required>
                </div>
                <div class="form-group">
                    <label for="tenantPhone">전화번호</label>
                    <input type="text" class="form-control" id="tenantPhone" name="phone">
                </div>
                <div class="form-group">
                    <label for="tenantEmail">이메일</label>
                    <input type="email" class="form-control" id="tenantEmail" name="email">
                </div>
                <div class="form-group">
                    <label for="tenantSocialNo">주민등록번호</label>
                    <input type="text" class="form-control" id="tenantSocialNo" name="socialNo">
                </div>
                <div class="form-group">
                    <label for="tenantCurrentAddress">현재 주소</label>
                    <input type="text" class="form-control" id="tenantCurrentAddress" name="currentAddress">
                </div>
                <button type="submit" class="btn btn-primary">등록</button>
                <a href="/tenants" class="btn btn-secondary">취소</a>
            </form>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
        $('#registerTenantForm').on('submit', function(e) {
            e.preventDefault();

            const tenantData = {
                name: $('#tenantName').val(),
                phone: $('#tenantPhone').val(),
                email: $('#tenantEmail').val(),
                socialNo: $('#tenantSocialNo').val(),
                currentAddress: $('#tenantCurrentAddress').val()
            };

            fetch('/tenants', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(tenantData)
            })
            .then(response => {
                if (response.ok) {
                    alert('세입자가 성공적으로 등록되었습니다.');
                    window.location.href = '/tenants';
                } else {
                    alert('세입자 등록에 실패했습니다.');
                }
            })
            .catch(error => console.error('Error creating tenant:', error));
        });
    });
</script>
</#macro>

