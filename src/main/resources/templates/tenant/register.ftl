<#include "../../common/layout.ftl">

<@layout "세입자 등록">

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">새 세입자 등록</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">세입자 정보 입력</h6>
        </div>
        <div class="card-body">
            <form id="tenantRegisterForm">
                <div class="form-group">
                    <label for="name">이름</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="phone">전화번호</label>
                    <input type="text" class="form-control" id="phone" name="phone">
                </div>
                <div class="form-group">
                    <label for="socialNo">사회보장번호</label>
                    <input type="text" class="form-control" id="socialNo" name="socialNo">
                </div>
                <div class="form-group">
                    <label for="currentAddress">현재 주소</label>
                    <input type="text" class="form-control" id="currentAddress" name="currentAddress">
                </div>
                <button type="submit" class="btn btn-primary">등록</button>
            </form>
        </div>
    </div>

</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('tenantRegisterForm').addEventListener('submit', function(event) {
            event.preventDefault();

            const formData = {
                name: document.getElementById('name').value,
                email: document.getElementById('email').value,
                phone: document.getElementById('phone').value,
                socialNo: document.getElementById('socialNo').value,
                currentAddress: document.getElementById('currentAddress').value
            };

            fetch('/api/tenants', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                if (data.id) {
                    alert('세입자가 성공적으로 등록되었습니다.');
                    window.location.href = '/tenants'; // Redirect to tenant list
                } else {
                    alert('세입자 등록 실패.');
                }
            })
            .catch(error => console.error('Error registering tenant:', error));
        });
    });
</script>

</@layout>