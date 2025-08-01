<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">부동산 등록</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">새 부동산 등록</h6>
        </div>
        <div class="card-body">
            <form action="/property/save" method="post">
                <div class="form-group">
                    <label for="name">이름</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="zipCode">우편번호</label>
                    <div class="input-group">
                        <input type="text" class="form-control" id="zipCode" name="zipCode" placeholder="우편번호" readonly required>
                        <div class="input-group-append">
                            <button type="button" class="btn btn-primary" id="searchAddressBtn" onClick="goPopup()">주소 검색</button>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="roadAddress">도로명 주소</label>
                    <input type="text" class="form-control" id="roadAddress" name="roadAddress" placeholder="도로명 주소" readonly required>
                </div>
                <div class="form-group">
                    <label for="detailAddress">상세 주소</label>
                    <input type="text" class="form-control" id="detailAddress" name="detailAddress" placeholder="상세 주소">
                </div>

                <div class="form-group">
                    <label for="type">유형</label>
                    <select class="form-control" id="type" name="type" required>
                        <option value="">유형 선택</option>
                        <option value="APARTMENT">아파트</option>
                        <option value="WKUp_VILLA">빌라</option>
                        <option value="OFFICE">오피스</option>
                        <option value="HOUSE">주택</option>
                        <option value="SHOP">상가</option>
                        <option value="LAND">토지</option>
                        <option value="FACTORY">공장</option>
                    </select>
                </div>
                <div class="form-group" id="totalUnitsInputGroup" style="display: none;">
                    <label for="totalUnits">총 세대수</label>
                    <input type="number" class="form-control" id="totalUnits" name="totalUnits" min="1">
                </div>
                <div class="form-group">
                    <label for="totalFloorsSelect">총 층수</label>
                    <select class="form-control" id="totalFloorsSelect" name="totalFloorsSelect" required>
                        <option value="1" selected>단층</option>
                        <option value="2">복층</option>
                        <option value="other">직접 입력</option>
                    </select>
                </div>
                <div class="form-group" id="totalFloorsInputGroup" style="display: none;">
                    <label for="totalFloors">층수 (숫자)</label>
                    <input type="number" class="form-control" id="totalFloors" name="totalFloors" min="1">
                </div>
                <button type="submit" class="btn btn-primary">부동산 저장</button>
            </form>
        </div>
    </div>

</div>

<script language="javascript">
// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
//document.domain = "abc.go.kr";

function goPopup(){
// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
var pop = window.open("/property/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes");

	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("property/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes");
}
function jusoCallBack(fullAddr, roadAddr, detailAddr, extraAddr, engAddr, jibunAddr, zipNo) {
console.log("jusoCallBack 호출:", fullAddr, zipNo);
document.getElementById("zipCode").value      = zipNo;
  document.getElementById("roadAddress").value   = roadAddr + (extraAddr? " "+ extraAddr : "");
  document.getElementById("detailAddress").value = detailAddr;
}

document.addEventListener('DOMContentLoaded', function () {
    var typeSelect = document.getElementById('type');
    var totalUnitsInputGroup = document.getElementById('totalUnitsInputGroup');
    var totalFloorsSelect = document.getElementById('totalFloorsSelect');
    var totalFloorsInputGroup = document.getElementById('totalFloorsInputGroup');

    typeSelect.addEventListener('change', function () {
        if (this.value === 'WKUp_VILLA') {
            totalUnitsInputGroup.style.display = 'block';
        } else {
            totalUnitsInputGroup.style.display = 'none';
        }
    });

    totalFloorsSelect.addEventListener('change', function () {
        if (this.value === 'other') {
            totalFloorsInputGroup.style.display = 'block';
        } else {
            totalFloorsInputGroup.style.display = 'none';
        }
    });
});
</script>

</#macro>