<#include "../common/layout.ftl">

<@layout "세입자 목록">
<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">세입자 목록</h1>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">등록된 세입자</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>이름</th>
                        <th>전화번호</th>
                        <th>이메일</th>
                        <th>주민등록번호</th>
                        <th>현재 주소</th>
                        <th>액션</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if tenants?? && (tenants?size > 0)>
                        <#list tenants as tenant>
                            <tr data-id="${tenant.id!}" class="tenant-row">
                                <td>${tenant.id!}</td>
                                <td>${tenant.name!}</td>
                                <td>${tenant.phone!}</td>
                                <td>${tenant.email!}</td>
                                <td>${tenant.socialNo!}</td>
                                <td>${tenant.currentAddress!}</td>
                                <td>
                                    <button class="btn btn-info btn-sm view-tenant" data-id="${tenant.id!}">상세</button>
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="7">세입자가 없습니다.</td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <a href="/tenant/register" class="btn btn-primary">세입자 등록</a>

</div>

<!-- Tenant Detail Modal -->
<div class="modal fade" id="tenantDetailModal" tabindex="-1" role="dialog" aria-labelledby="tenantDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="tenantDetailModalLabel">세입자 상세 정보</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="tenantDetailForm">
                    <input type="hidden" id="tenantId" name="id">
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
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">닫기</button>
                <button class="btn btn-primary" id="saveTenantChanges">변경 사항 저장</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        // DataTables 초기화
        $('#dataTable').DataTable();

        // 행 클릭 또는 상세 버튼 클릭 시 모달 열기
        $(document).on('click', '.tenant-row, .view-tenant', function() {
            const tenantId = $(this).data('id');
            if (tenantId) {
                fetch(`/tenants/${tenantId}`)
                    .then(response => response.json())
                    .then(data => {
                        $('#tenantId').val(data.id);
                        $('#tenantName').val(data.name);
                        $('#tenantPhone').val(data.phone);
                        $('#tenantEmail').val(data.email);
                        $('#tenantSocialNo').val(data.socialNo);
                        $('#tenantCurrentAddress').val(data.currentAddress);
                        $('#tenantDetailModal').modal('show');
                    })
                    .catch(error => console.error('Error fetching tenant details:', error));
            }
        });

        // 변경 사항 저장 버튼 클릭 시
        $('#saveTenantChanges').on('click', function() {
            const tenantId = $('#tenantId').val();
            const tenantData = {
                id: tenantId,
                name: $('#tenantName').val(),
                phone: $('#tenantPhone').val(),
                email: $('#tenantEmail').val(),
                socialNo: $('#tenantSocialNo').val(),
                currentAddress: $('#tenantCurrentAddress').val()
            };

            fetch(`/tenants/${tenantId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(tenantData)
            })
            .then(response => {
                if (response.ok) {
                    alert('세입자 정보가 성공적으로 업데이트되었습니다.');
                    $('#tenantDetailModal').modal('hide');
                    location.reload(); // 페이지 새로고침하여 변경된 데이터 반영
                } else {
                    alert('세입자 정보 업데이트에 실패했습니다.');
                }
            })
            .catch(error => console.error('Error updating tenant:', error));
        });
    });
</script>
</#macro>
</@layout>