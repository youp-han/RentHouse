<#include "../common/layout.ftl">

<#macro content>
<div class="container-fluid">

    <h1 class="h3 mb-2 text-gray-800"><#if bill??>청구 항목 수정<#else>신규 청구 항목 등록</#if></h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">청구 항목 정보</h6>
        </div>
        <div class="card-body">
            <form id="billForm" <#if bill??>action="/bills/update" method="post"<#else>action="/bills" method="post"</#if>>
                <#if bill??><input type="hidden" name="id" value="${bill.id}"></#if>
                <div class="form-group">
                    <label for="name">이름</label>
                    <input type="text" class="form-control" id="name" name="name" required value="<#if bill??>${bill.name}</#if>">
                </div>
                <div class="form-group">
                    <label for="category">카테고리</label>
                    <select class="form-control" id="category" name="category" required>
                        <option value="">카테고리를 선택하세요</option>
                        <#list billCategories as category>
                            <option value="${category}" <#if bill?? && bill.category == category>selected</#if>>${category.name()}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group">
                    <label for="amount">금액</label>
                    <input type="number" class="form-control" id="amount" name="amount" required value="<#if bill??>${bill.amount?string("0.00")}</#if>">
                </div>
                <div class="form-group">
                    <label for="description">설명</label>
                    <textarea class="form-control" id="description" name="description" rows="3"><#if bill??>${bill.description}</#if></textarea>
                </div>
                <button type="submit" class="btn btn-primary"><#if bill??>업데이트<#else>등록</#if></button>
                <#if bill??>
                    <button type="button" class="btn btn-danger" id="deleteBillBtn">삭제</button>
                </#if>
                <a href="/bills" class="btn btn-secondary">취소</a>
            </form>
        </div>
    </div>

</div>

<script>
$(document).ready(function() {
    <#if bill??>
    $('#deleteBillBtn').on('click', function() {
        if (confirm('정말로 이 청구 항목을 삭제하시겠습니까?')) {
            $.post('/bills/delete/${bill.id}', function() {
                window.location.href = '/bills';
            }).fail(function() {
                alert('청구 항목 삭제에 실패했습니다.');
            });
        }
    });
    </#if>
});
</script>
</#macro>