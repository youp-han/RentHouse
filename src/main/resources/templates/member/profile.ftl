<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-4 text-gray-800">내 프로필</h1>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">회원 정보</h6>
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
            <p><strong>이름:</strong> ${member.name!}</p>
            <p><strong>이메일:</strong> ${member.email!}</p>
            <p><strong>전화번호:</strong> ${member.phoneNumber!}</p>
            <p><strong>역할:</strong> ${member.role!}</p>
            <p><strong>가입일:</strong> ${createTimeFormatted!}</p>

            <a href="/member/settings" class="btn btn-info">정보 수정</a>

            <hr>

            <h6 class="m-0 font-weight-bold text-danger">회원 탈퇴</h6>
            <p>회원 탈퇴를 원하시면 아래 버튼을 클릭하십시오. 모든 정보가 삭제됩니다.</p>
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteAccountModal">
                회원 탈퇴
            </button>
        </div>
    </div>

</div>

<!-- Delete Account Modal-->
<div class="modal fade" id="deleteAccountModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">정말 탈퇴하시겠습니까?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">회원 탈퇴 시 모든 정보가 삭제되며 복구할 수 없습니다. 정말 탈퇴하시겠습니까?</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                <form action="/member/delete" method="post">
                    <button class="btn btn-danger" type="submit">탈퇴</button>
                </form>
            </div>
        </div>
    </div>
</div>
</#macro>

<#include "../common/layout.ftl">