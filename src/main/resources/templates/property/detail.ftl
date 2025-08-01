<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">부동산 상세 정보</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">부동산 정보</h6>
        </div>
        <div class="card-body">
            <#if property??>
                <form id="propertyDetailForm">
                    <input type="hidden" id="propertyId" name="id" value="${property.id!}">
                    <div class="form-group">
                        <label for="name">이름</label>
                        <input type="text" class="form-control" id="name" name="name" value="${property.name!}" required>
                    </div>
                    <div class="form-group">
                        <label for="address">주소</label>
                        <input type="text" class="form-control" id="address" name="address" value="${property.address!}" required>
                    </div>
                    <div class="form-group">
                        <label for="type">유형</label>
                        <select class="form-control" id="type" name="type">
                            <option value="APARTMENT" <#if property.type! == "APARTMENT">selected</#if>>APARTMENT</option>
                            <option value="BUILDING" <#if property.type! == "BUILDING">selected</#if>>BUILDING</option>
                            <!-- Add other property types as needed -->
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="totalFloors">총 층수</label>
                        <input type="number" class="form-control" id="totalFloors" name="totalFloors" value="${property.totalFloors!}">
                    </div>
                    <button type="submit" class="btn btn-primary">정보 업데이트</button>
                    <button type="button" class="btn btn-danger" id="deletePropertyBtn" data-property-id="${property.id!}">부동산 삭제</button>
                </form>

                <hr>

                <h6 class="m-0 font-weight-bold text-primary">유닛 목록</h6>
                <a href="/property/unit/register?propertyId=${property.id!}" class="btn btn-success btn-sm mb-3">새 유닛 추가</a>
                <div class="table-responsive">
                    <table class="table table-bordered" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>유닛 번호</th>
                            <th>임대 상태</th>
                            <th>면적 (㎡)</th>
                            <th>면적 (평)</th>
                            <th>사용 유형</th>
                            <th>설명</th>
                            <th>액션</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if property.units?? && property.units?size > 0>
                            <#list property.units as unit>
                                <tr>
                                    <td>${unit.id!}</td>
                                    <td>${unit.unitNumber!}</td>
                                    <td>${unit.rentStatus?string("임대 중", "공실")}</td>
                                    <td>${unit.size_meter!}</td>
                                    <td>${unit.size_korea!}</td>
                                    <td>${unit.useType!}</td>
                                    <td>${unit.description!}</td>
                                    <td>
                                        <a href="/property/unit/detail/${unit.id!}" class="btn btn-info btn-sm">편집</a>
                                        <button class="btn btn-danger btn-sm delete-unit-btn" data-unit-id="${unit.id!}">삭제</button>
                                    </td>
                                </tr>
                            </#list>
                        <#else>
                            <tr>
                                <td colspan="8">등록된 유닛이 없습니다.</td>
                            </tr>
                        </#if>
                        </tbody>
                    </table>
                </div>

            <#else>
                <p>부동산 정보를 찾을 수 없습니다.</p>
            </#if>
        </div>
    </div>

</div>

<script>
    $(document).ready(function() {
        // Property Update Form Submission
        $('#propertyDetailForm').on('submit', function(event) {
            event.preventDefault();

            const propertyId = $('#propertyId').val();
            const formData = {
                id: propertyId,
                name: $('#name').val(),
                address: $('#address').val(),
                type: $('#type').val(),
                totalFloors: $('#totalFloors').val()
            };

            fetch(`/property/properties/${propertyId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                if (data.id) {
                    alert('부동산 정보가 성공적으로 업데이트되었습니다.');
                    location.reload();
                } else {
                    alert('부동산 정보 업데이트 실패.');
                }
            })
            .catch(error => console.error('Error updating property:', error));
        });

        // Property Delete Button
        $('#deletePropertyBtn').on('click', function() {
            const propertyId = $(this).data('property-id');
            if (confirm('이 부동산을 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
                fetch(`/property/properties/${propertyId}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    if (response.ok) {
                        alert('부동산이 성공적으로 삭제되었습니다.');
                        window.location.href = '/property/propertyList';
                    } else {
                        alert('부동산 삭제 실패.');
                    }
                })
                .catch(error => console.error('Error deleting property:', error));
            }
        });

        // Unit Delete Button (Delegation for dynamically added elements)
        $(document).on('click', '.delete-unit-btn', function() {
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
                        location.reload();
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