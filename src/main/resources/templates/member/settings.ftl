<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">회원 정보 설정</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">개인 정보 수정</h6>
        </div>
        <div class="card-body">
            <#if message??>
                <div class="alert alert-success" role="alert">
                    ${message}
                </div>
            </#if>
            <#if error??>
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </#if>
            <form action="/member/settings" method="post">
                <div class="form-group">
                    <label for="name">이름</label>
                    <input type="text" class="form-control" id="name" name="name" value="${member.name!}" required>
                </div>
                <div class="form-group">
                    <label for="email">이메일 (수정 불가)</label>
                    <input type="email" class="form-control" id="email" name="email" value="${member.email!}" readonly>
                </div>
                <div class="form-group">
                    <label for="phoneNumber">전화번호</label>
                    <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${member.phoneNumber!}">
                </div>
                <div class="form-group">
                    <label for="role">역할 (수정 불가)</label>
                    <input type="text" class="form-control" id="role" name="role" value="${member.role!}" readonly>
                </div>
                <button type="submit" class="btn btn-primary">정보 업데이트</button>
            </form>

            <hr>

            <h6 class="m-0 font-weight-bold text-primary">비밀번호 변경</h6>
            <form action="/member/change-password" method="post" class="mt-3">
                <div class="form-group">
                    <label for="currentPassword">현재 비밀번호</label>
                    <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                </div>
                <div class="form-group">
                    <label for="newPassword">새 비밀번호</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                </div>
                <div class="form-group">
                    <label for="confirmNewPassword">새 비밀번호 확인</label>
                    <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" required>
                </div>
                <button type="submit" class="btn btn-warning">비밀번호 변경</button>
            </form>
        </div>
    </div>

</div>
</#macro>

<#include "../common/layout.ftl">
