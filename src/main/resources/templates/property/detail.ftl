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
                        <input type="text" class="form-control" id="address" name="address" value="${property.address!}" required readonly>
                    </div>
                    <div class="form-group">
                        <label for="type">유형</label>
                        <select class="form-control" id="type" name="type">
                            <option value="APARTMENT" <#if property.type! == "APARTMENT">selected</#if>>아파트</option>
                            <option value="WKUp_VILLA" <#if property.type! == "WKUp_VILLA">selected</#if>>빌라</option>
                            <option value="OFFICE" <#if property.type! == "OFFICE">selected</#if>>오피스</option>
                            <option value="HOUSE" <#if property.type! == "HOUSE">selected</#if>>주택</option>
                            <option value="SHOP" <#if property.type! == "SHOP">selected</#if>>상가</option>
                            <option value="LAND" <#if property.type! == "LAND">selected</#if>>토지</option>
                            <option value="FACTORY" <#if property.type! == "FACTORY">selected</#if>>공장</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="totalFloors">총 층수</label>
                        <input type="number" class="form-control" id="totalFloors" name="totalFloors" value="${property.totalFloors!}">
                    </div>
                    <div class="form-group">
                        <label for="totalUnits">총 세대수</label>
                        <input type="number" class="form-control" id="totalUnits" name="totalUnits" value="${property.totalUnits!}">
                    </div>
                    <button type="button" class="btn btn-primary" id="updatePropertyBtn">정보 업데이트</button>
                    <button type="button" class="btn btn-danger" id="deletePropertyBtn" data-property-id="${property.id!}">부동산 삭제</button>
                </form>

                <hr>

                <h6 class="m-0 font-weight-bold text-primary">유닛 목록</h6>
                <button type="button" class="btn btn-success btn-sm mb-3" id="addUnitBtn">새 유닛 추가</button>
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
                        <#if property.units?? && property.units?size gt 0>
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
                                        <button class="btn btn-info btn-sm edit-unit-btn" data-unit-id="${unit.id!}">편집</button>
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

<!-- Unit Modal -->
<div class="modal fade" id="unitModal" tabindex="-1" role="dialog" aria-labelledby="unitModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="unitModalLabel">유닛 정보</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="unitForm">
                    <input type="hidden" id="unitId" name="id">
                    <input type="hidden" id="propertyIdForUnit" name="propertyId" value="${property.id!}">
                    <div class="form-group">
                        <label for="unitNumber">유닛 번호</label>
                        <input type="text" class="form-control" id="unitNumber" name="unitNumber" required>
                    </div>
                    <div class="form-group">
                        <label for="rentStatus">임대 상태</label>
                        <select class="form-control" id="rentStatus" name="rentStatus">
                            <option value="true">임대 중</option>
                            <option value="false">공실</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="size_meter">면적 (㎡)</label>
                        <input type="number" class="form-control" id="size_meter" name="size_meter">
                    </div>
                    <div class="form-group">
                        <label for="size_korea">면적 (평)</label>
                        <input type="number" class="form-control" id="size_korea" name="size_korea">
                    </div>
                    <div class="form-group">
                        <label for="useType">사용 유형</label>
                        <input type="text" class="form-control" id="useType" name="useType">
                    </div>
                    <div class="form-group">
                        <label for="description">설명</label>
                        <textarea class="form-control" id="description" name="description"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                <button type="button" class="btn btn-primary" id="saveUnitBtn">저장</button>
            </div>
        </div>
    </div>
</div>

<script>
$(document).ready(function() {
    // Show Add Unit Modal
    $('#addUnitBtn').on('click', function() {
        $('#unitForm')[0].reset();
        $('#unitId').val('');
        $('#unitModalLabel').text('새 유닛 추가');
        $('#unitModal').modal('show');
    });

    // Show Edit Unit Modal
    $('.edit-unit-btn').on('click', function() {
        var unitId = $(this).data('unit-id');

        
        // Fetch unit data from server
        fetch(`/property/units/` + unitId)
            .then(response => response.json())
            .then(unit => {
                $('#unitId').val(unit.id);
                $('#unitNumber').val(unit.unitNumber);
                $('#rentStatus').val(unit.rentStatus.toString());
                $('#size_meter').val(unit.size_meter);
                $('#size_korea').val(unit.size_korea);
                $('#useType').val(unit.useType);
                $('#description').val(unit.description);

                $('#unitModalLabel').text('유닛 정보 수정');
                $('#unitModal').modal('show');
            });
    });

    // Save or Update Unit
    $('#saveUnitBtn').on('click', function() {
        var unitId = $('#unitId').val();
        var method = unitId ? 'PUT' : 'POST';
        var url = unitId ? `/property/units/` + unitId : '/property/unit/save';

        var formData = {
            id: unitId,
            propertyId: $('#propertyIdForUnit').val(),
            unitNumber: $('#unitNumber').val(),
            rentStatus: $('#rentStatus').val() === 'true',
            size_meter: $('#size_meter').val(),
            size_korea: $('#size_korea').val(),
            useType: $('#useType').val(),
            description: $('#description').val()
        };

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                alert('유닛 정보가 저장되었습니다.');
                location.reload();
            } else {
                alert('유닛 정보 저장에 실패했습니다.');
            }
        });
    });

    // Delete Unit
    $('.delete-unit-btn').on('click', function() {
        var unitId = $(this).data('unit-id');
        if (confirm('이 유닛을 삭제하시겠습니까?')) {
            fetch(`/property/units/` + unitId, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    alert('유닛이 삭제되었습니다.');
                    location.reload();
                } else {
                    alert('유닛 삭제에 실패했습니다.');
                }
            });
        }
    });

    // Update Property
    $('#updatePropertyBtn').on('click', function() {
        var propertyId = $('#propertyId').val();
        var formData = {
            name: $('#name').val(),
            type: $('#type').val(),
            totalFloors: $('#totalFloors').val(),
            totalUnits: $('#totalUnits').val()
        };

        fetch(`/property/properties/`+ propertyId, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                alert('부동산 정보가 업데이트되었습니다.');
                location.reload();
            } else {
                alert('부동산 정보 업데이트에 실패했습니다.');
            }
        });
    });

    // Delete Property
    $('#deletePropertyBtn').on('click', function() {
        var propertyId = $(this).data('property-id');
        if (confirm('이 부동산을 삭제하시겠습니까?')) {
            fetch(`/property/properties/`+ propertyId, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    alert('부동산이 삭제되었습니다.');
                    window.location.href = '/property/propertyList';
                } else {
                    alert('부동산 삭제에 실패했습니다.');
                }
            });
        }
    });
});
</script>

</#macro>