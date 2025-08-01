<#include "../../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">유닛 등록</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">새 유닛 등록</h6>
        </div>
        <div class="card-body">
            <form action="/property/unit/save" method="post">
                <input type="hidden" id="propertyId" name="propertyId" value="${property.id!}" />
                <div class="form-group">
                    <label for="propertyAddress">부동산 주소:</label>
                    <input type="text" class="form-control" id="propertyAddress" value="${property.address!}" readonly>
                </div>
                <div class="form-group">
                    <label for="unitNumber">유닛 번호:</label>
                    <input type="text" class="form-control" id="unitNumber" name="unitNumber" required>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="rentStatus" name="rentStatus" value="true">
                    <label class="form-check-label" for="rentStatus">임대 중</label>
                </div>
                <div class="form-group">
                    <label for="size_meter">면적 (㎡):</label>
                    <input type="number" step="0.01" class="form-control" id="size_meter" name="size_meter">
                </div>
                <div class="form-group">
                    <label for="size_korea">면적 (평):</label>
                    <input type="number" step="0.01" class="form-control" id="size_korea" name="size_korea">
                </div>
                <div class="form-group">
                    <label for="useType">사용 유형:</label>
                    <select class="form-control" id="useType" name="useType" required>
                        <option value="">유형 선택</option>
                        <option value="RESIDENTIAL">주거</option>
                        <option value="COMMERCIAL">상업</option>
                        <option value="OFFICE">사무실</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="description">설명:</label>
                    <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">유닛 저장</button>
            </form>
        </div>
    </div>

</div>
</#macro>