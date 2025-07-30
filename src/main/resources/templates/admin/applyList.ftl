<#include "../common/layout.ftl">

<@layout "신규 회원 목록">

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">신규 회원 목록</h1>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">승인 대기 중인 회원</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>이름</th>
                        <th>이메일</th>
                        <th>전화번호</th>
                        <th>가입일</th>
                        <th>액션</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if newMembers??>
                        <#list newMembers as member>
                            <tr>
                                <td>${member.id!}</td>
                                <td>${member.name!}</td>
                                <td>${member.email!}</td>
                                <td>${member.phoneNumber!}</td>
                                <td>${member.createdDate?string("yyyy-MM-dd HH:mm")!}</td>
                                <td>
                                    <button class="btn btn-success btn-sm approve-member-btn" data-member-id="${member.id!}">승인</button>
                                    <button class="btn btn-danger btn-sm reject-member-btn" data-member-id="${member.id!}">거절</button>
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="6">신규 회원이 없습니다.</td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.querySelectorAll('.approve-member-btn').forEach(button => {
            button.addEventListener('click', function() {
                const memberId = this.dataset.memberId;
                if (confirm('이 회원을 승인하시겠습니까?')) {
                    fetch(`/admin/member/approve/${memberId}`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (data.status === 'success') {
                            alert('회원이 성공적으로 승인되었습니다.');
                            location.reload();
                        } else {
                            alert('회원 승인 실패: ' + data.message);
                        }
                    })
                    .catch(error => console.error('Error approving member:', error));
                }
            });
        });

        document.querySelectorAll('.reject-member-btn').forEach(button => {
            button.addEventListener('click', function() {
                const memberId = this.dataset.memberId;
                if (confirm('이 회원을 거절하고 삭제하시겠습니까?')) {
                    fetch(`/admin/member/reject/${memberId}`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    })
                    .then(response => response.json())
                    .then(data => {
                        if (data.status === 'success') {
                            alert('회원이 성공적으로 거절 및 삭제되었습니다.');
                            location.reload();
                        } else {
                            alert('회원 거절 및 삭제 실패: ' + data.message);
                        }
                    })
                    .catch(error => console.error('Error rejecting member:', error));
                }
            });
        });
    });
</script>

</@layout>