<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">임대차 계약 등록</h1>

    <!-- Lease Registration Form -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">신규 임대차 계약 정보</h6>
        </div>
        <div class="card-body">
            <form id="registerLeaseForm">
                <div class="form-group">
                    <label for="tenantId">임차인</label>
                    <select class="form-control" id="tenantId" name="tenantId" required>
                        <option value="">임차인을 선택하세요</option>
                        <#list tenants as tenant>
                            <option value="${tenant.id}">${tenant.name} (${tenant.email})</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="propertyId">부동산</label>
                    <select class="form-control" id="propertyId" name="propertyId" required>
                        <option value="">부동산을 선택하세요</option>
                        <#list properties as property>
                            <option value="${property.id}">${property.name}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="unitId">임대 유닛</label>
                    <select class="form-control" id="unitId" name="unitId" required>
                        <option value="">부동산을 먼저 선택하세요</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="leaseType">계약 종류</label>
                    <select class="form-control" id="leaseType" name="leaseType" required>
                        <option value="">계약 종류를 선택하세요</option>
                        <#list leaseTypes as type>
                            <option value="${type}">${type.name()}</option>
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
                <button type="submit" class="btn btn-primary">등록</button>
                <a href="/leases" class="btn btn-secondary">취소</a>
            </form>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
        $('#propertyId').on('change', function() {
            const propertyId = $(this).val();
            const unitSelect = $('#unitId');
            unitSelect.empty().append('<option value="">불러오는 중...</option>');

            if (propertyId) {
                    fetch(`/property/getUnitList/`+ propertyId)
                    .then(response => response.json())
                    .then(units => {
                        unitSelect.empty().append('<option value="">임대 유닛을 선택하세요</option>');
                        units.forEach(function(unit) {
                            var option = $('<option></option>').val(unit.id).text(unit.unitNumber);
                            unitSelect.append(option);
                        });
                    })
                    .catch(error => {
                        console.error('Error fetching units:', error);
                        unitSelect.empty().append('<option value="">유닛을 불러오는데 실패했습니다.</option>');
                    });
            } else {
                unitSelect.empty().append('<option value="">부동산을 먼저 선택하세요</option>');
            }
        });

        $('#registerLeaseForm').on('submit', function(e) {
            e.preventDefault();

            const leaseData = {
                tenantId: $('#tenantId').val(),
                unitId: $('#unitId').val(),
                leaseType: $('#leaseType').val(),
                startDate: $('#startDate').val(),
                endDate: $('#endDate').val(),
                deposit: $('#deposit').val(),
                monthlyRent: $('#monthlyRent').val(),
                contractNotes: $('#contractNotes').val()
            };

            fetch('/leases', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(leaseData)
            })
            .then(response => {
                if (response.ok) {
                    alert('임대차 계약이 성공적으로 등록되었습니다.');
                    window.location.href = '/leases';
                } else {
                    alert('임대차 계약 등록에 실패했습니다.');
                }
            })
            .catch(error => console.error('Error creating lease:', error));
        });
    });
</script>
</#macro>

