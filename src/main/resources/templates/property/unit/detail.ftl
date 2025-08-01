<#include "../../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">유닛 상세 정보</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">유닛 정보</h6>
        </div>
        <div class="card-body">
            <#if unit??>
                <form id="unitDetailForm">
                    <input type="hidden" id="unitId" name="id" value="${unit.id!}">
                    <div class="form-group">
                        <label for="unitNumber">유닛 번호</label>
                        <input type="text" class="form-control" id="unitNumber" name="unitNumber" value="${unit.unitNumber!}" required>
                    </div>
                    <div class="form-group">
                        <label for="rentStatus">임대 상태</label>
                        <select class="form-control" id="rentStatus" name="rentStatus">
                            <option value="true" <#if unit.rentStatus!>selected</#if>>임대 중</option>
                            <option value="false" <#if !unit.rentStatus!>selected</#if>>공실</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="size_meter">면적 (㎡)</label>
                        <input type="number" step="0.01" class="form-control" id="size_meter" name="size_meter" value="${unit.size_meter!}">
                    </div>
                    <div class="form-group">
                        <label for="size_korea">면적 (평)</label>
                        <input type="number" step="0.01" class="form-control" id="size_korea" name="size_korea" value="${unit.size_korea!}">
                    </div>
                    <div class="form-group">
                        <label for="useType">사용 유형</label>
                        <input type="text" class="form-control" id="useType" name="useType" value="${unit.useType!}">
                    </div>
                    <div class="form-group">
                        <label for="description">설명</label>
                        <textarea class="form-control" id="description" name="description" rows="3">${unit.description!}</textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">정보 업데이트</button>
                    <button type="button" class="btn btn-danger" id="deleteUnitBtn" data-unit-id="${unit.id!}">유닛 삭제</button>
                </form>
            <#else>
                <p>유닛 정보를 찾을 수 없습니다.</p>
            </#if>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
        // Unit Update Form Submission
        $('#unitDetailForm').on('submit', function(event) {
            event.preventDefault();

            const unitId = $('#unitId').val();
            const formData = {
                id: unitId,
                unitNumber: $('#unitNumber').val(),
                rentStatus: $('#rentStatus').val() === 'true',
                size_meter: $('#size_meter').val(),
                size_korea: $('#size_korea').val(),
                useType: $('#useType').val(),
                description: $('#description').val()
            };

            fetch(`/property/units/${unitId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                if (data.id) {
                    alert('유닛 정보가 성공적으로 업데이트되었습니다.');
                    // Optionally redirect or update UI
                } else {
                    alert('유닛 정보 업데이트 실패.');
                }
            })
            .catch(error => console.error('Error updating unit:', error));
        });

        // Unit Delete Button
        $('#deleteUnitBtn').on('click', function() {
            const unitId = $(this).data('unit-id');
            if (confirm('이 유닛을 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
                fetch(`/property/units/${unitId}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    if (response.ok) {
                        alert('유닛이 성공적으로 삭제되었습니다.');
                        window.location.href = '/property/detail/' + unitId; // Redirect to property detail after deletion
                    } else {
                        alert('유닛 삭제 실패.');
                    }
                })
                .catch(error => console.error('Error deleting unit:', error));
            }
        });
    });
</script>

</#macro>