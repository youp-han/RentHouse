<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <h1 class="h3 mb-2 text-gray-800">신규 청구 항목 등록</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">청구 항목 정보</h6>
        </div>
        <div class="card-body">
            <form id="registerBillForm">
                <div class="form-group">
                    <label for="name">이름</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="category">카테고리</label>
                    <select class="form-control" id="category" name="category" required>
                        <option value="">카테고리를 선택하세요</option>
                        <#list billCategories as category>
                            <option value="${category}">${category.name()}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="amount">금액</label>
                    <input type="number" class="form-control" id="amount" name="amount" required>
                </div>
                <div class="form-group">
                    <label for="description">설명</label>
                    <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">등록</button>
                <a href="/bills" class="btn btn-secondary">취소</a>
            </form>
        </div>
    </div>

</div>

<script>
$(document).ready(function() {
    $('#registerBillForm').on('submit', function(e) {
        e.preventDefault();

        const billData = {
            name: $('#name').val(),
            category: $('#category').val(),
            amount: $('#amount').val(),
            description: $('#description').val()
        };

        fetch('/bills', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(billData)
        })
        .then(response => {
            if (response.ok) {
                if (confirm('등록이 완료되었습니다. 다음 항목을 계속 등록하시겠습니까?')) {
                    $('#registerBillForm')[0].reset();
                } else {
                    window.location.href = '/bills';
                }
            } else {
                alert('청구 항목 등록에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error creating bill:', error);
            alert('청구 항목 등록 중 오류가 발생했습니다.');
        });
    });
});
</script>
</#macro>
