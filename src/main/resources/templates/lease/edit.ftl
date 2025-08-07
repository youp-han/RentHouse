<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">임대차 계약 편집</h1>

    <!-- Lease Edit Form -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">임대차 계약 정보</h6>
        </div>
        <div class="card-body">
            <form id="editLeaseForm">
                <input type="hidden" id="leaseId" name="id">
                <div class="form-group">
                    <label for="tenantName">임차인</label>
                    <input type="text" class="form-control" id="tenantName" readonly>
                </div>
                <div class="form-group">
                    <label for="propertyName">부동산</label>
                    <input type="text" class="form-control" id="propertyName" readonly>
                </div>
                <div class="form-group">
                    <label for="unitNumber">임대 유닛</label>
                    <input type="text" class="form-control" id="unitNumber" readonly>
                </div>
                <div class="form-group">
                    <label for="leaseTypeDisplay">계약 종류</label>
                    <input type="text" class="form-control" id="leaseTypeDisplay" readonly>
                </div>
                <div class="form-group">
                    <label for="leaseStatus">계약 상태</label>
                    <select class="form-control" id="leaseStatus" name="leaseStatus" required>
                        <option value="">계약 상태를 선택하세요</option>
                        <#list leaseStatusTypes as status>
                            <option value="${status}">${status.name()}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="startDate">시작일</label>
                    <input type="date" class="form-control" id="startDate" name="startDate" required>
                </div>
                <div class="form-group">
                    <label for="endDate">종료일</label>
                    <input type="date" class="form-control" id="endDate" name="endDate" required>
                </div>
                <div class="form-group">
                    <label for="deposit">보증금</label>
                    <input type="number" class="form-control" id="deposit" name="deposit">
                </div>
                <div class="form-group">
                    <label for="monthlyRent">월세</label>
                    <input type="number" class="form-control" id="monthlyRent" name="monthlyRent">
                </div>
                <div class="form-group">
                    <label for="contractNotes">계약 비고</label>
                    <textarea class="form-control" id="contractNotes" name="contractNotes" rows="3"></textarea>
                </div>
                <button type="button" class="btn btn-primary" id="saveLeaseBtn">저장</button>
                <button type="button" class="btn btn-danger" id="deleteLeaseBtn">삭제</button>
                <a href="/leases" class="btn btn-secondary">목록으로 돌아가기</a>
            </form>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
        const leaseId = window.location.pathname.split('/').pop();

        // Load lease data
        fetch(`/leases/` + leaseId)
            .then(response => response.json())
            .then(lease => {
                $('#leaseId').val(lease.id);
                $('#tenantName').val(lease.tenantName);
                $('#propertyName').val(lease.propertyName);
                $('#unitNumber').val(lease.unitNumber);
                $('#leaseTypeDisplay').val(lease.leaseType);
                $('#leaseStatus').val(lease.leaseStatus);
                $('#startDate').val(lease.startDate);
                $('#endDate').val(lease.endDate);
                $('#deposit').val(lease.deposit);
                $('#monthlyRent').val(lease.monthlyRent);
                $('#contractNotes').val(lease.contractNotes);
            })
            .catch(error => console.error('Error loading lease data:', error));

        // Save Lease Changes
        $('#saveLeaseBtn').on('click', function() {
            const updatedLeaseData = {
                startDate: $('#startDate').val(),
                endDate: $('#endDate').val(),
                deposit: $('#deposit').val(),
                monthlyRent: $('#monthlyRent').val(),
                contractNotes: $('#contractNotes').val(),
                leaseStatus: $('#leaseStatus').val()
            };

            fetch(`/leases/`+leaseId, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedLeaseData)
            })
            .then(response => {
                if (response.ok) {
                    alert('임대차 계약 정보가 성공적으로 업데이트되었습니다.');
                    window.location.href = '/leases';
                } else {
                    alert('임대차 계약 정보 업데이트에 실패했습니다.');
                }
            })
            .catch(error => console.error('Error updating lease:', error));
        });

        // Delete Lease
        $('#deleteLeaseBtn').on('click', function() {
            if (confirm('정말로 이 임대차 계약을 삭제하시겠습니까?')) {
                fetch(`/leases/`+leaseId, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        alert('임대차 계약이 성공적으로 삭제되었습니다.');
                        window.location.href = '/leases';
                    } else {
                        alert('임대차 계약 삭제에 실패했습니다.');
                    }
                })
                .catch(error => console.error('Error deleting lease:', error));
            }
        });
    });
</script>
</#macro>