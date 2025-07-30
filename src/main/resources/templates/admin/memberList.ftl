<#include "../common/layout.ftl">
<#macro content>
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">회원 목록</h1>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">등록된 회원</h6>
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
                        <th>역할</th>
                        <th>승인 여부</th>
                        <th>신규 여부</th>
                        <th>삭제 여부</th>
                        <th>액션</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if members?? && (members?size > 0)>
                        <#list members as member>
                            <tr data-id="${member.id!}" class="member-row">
                                <td>${member.id!}</td>
                                <td>${member.name!}</td>
                                <td>${member.email!}</td>
                                <td>${member.phoneNumber!}</td>
                                <td>${member.role!}</td>
                                <td>${member.approved?string("Yes", "No")}</td>
                                <td>${member.newUser?string("Yes", "No")}</td>
                                <td>${member.deleted?string("Yes", "No")}</td>
                                <td>
                                    <button class="btn btn-info btn-sm view-member" data-id="${member.id!}">상세</button>
                                </td>
                            </tr>
                        </#list>
                    <#else>
                        <tr>
                            <td colspan="9">회원이 없습니다.</td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

<!-- Member Detail Modal -->
<div class="modal fade" id="memberDetailModal" tabindex="-1" role="dialog" aria-labelledby="memberDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="memberDetailModalLabel">회원 상세 정보</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="memberDetailForm">
                    <input type="hidden" id="memberId" name="id">
                    <div class="form-group">
                        <label for="memberName">이름</label>
                        <input type="text" class="form-control" id="memberName" name="name" readonly>
                    </div>
                    <div class="form-group">
                        <label for="memberEmail">이메일</label>
                        <input type="email" class="form-control" id="memberEmail" name="email" readonly>
                    </div>
                    <div class="form-group">
                        <label for="memberPhoneNumber">전화번호</label>
                        <input type="text" class="form-control" id="memberPhoneNumber" name="phoneNumber">
                    </div>
                    <div class="form-group">
                        <label for="memberRole">역할</label>
                        <select class="form-control" id="memberRole" name="role">
                            <option value="ADMIN">ADMIN</option>
                            <option value="MEMBER">MEMBER</option>
                            <option value="LANDLORD">LANDLORD</option>
                            <option value="TENANT">TENANT</option>
                        </select>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="memberApproved" name="approved">
                        <label class="form-check-label" for="memberApproved">승인됨</label>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="memberNew" name="newUser" disabled>
                        <label class="form-check-label" for="memberNew">신규 회원</label>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="memberDeleted" name="deleted">
                        <label class="form-check-label" for="memberDeleted">삭제됨</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">닫기</button>
                <button class="btn btn-danger" id="deleteMemberBtn">회원 삭제</button>
                <button class="btn btn-warning" id="resetPasswordBtn">비밀번호 초기화</button>
                <button class="btn btn-primary" id="saveMemberChanges">변경 사항 저장</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        // DataTables 초기화
        $('#dataTable').DataTable();

        // 행 클릭 또는 상세 버튼 클릭 시 모달 열기
        $(document).on('click', '.member-row, .view-member', function() {
            console.log("상세 버튼 또는 행 클릭됨!"); // 추가
            const memberId = $(this).data('id');
            console.log("Member ID:", memberId); // 추가
            if (memberId) {
                    fetch(`/admin/member/` + memberId)
                    .then(response => response.json())
                    .then(data => {
                        $('#memberId').val(data.id);
                        $('#memberName').val(data.name);
                        $('#memberEmail').val(data.email);
                        $('#memberPhoneNumber').val(data.phoneNumber);
                        $('#memberRole').val(data.role);
                        $('#memberApproved').prop('checked', data.approved);
                        $('#memberNew').prop('checked', data.newUser);
                        $('#memberDeleted').prop('checked', data.deleted);
                        $('#memberDetailModal').modal('show');
                    })
                    .catch(error => console.error('Error fetching member details:', error));
            }
        });

        // 변경 사항 저장 버튼 클릭 시
        $('#saveMemberChanges').on('click', function() {
            const memberId = $('#memberId').val();
            const memberData = {
                id: memberId,
                name: $('#memberName').val(),
                email: $('#memberEmail').val(),
                phoneNumber: $('#memberPhoneNumber').val(),
                role: $('#memberRole').val(),
                approved: $('#memberApproved').is(':checked'),
                newUser: $('#memberNew').is(':checked'),
                deleted: $('#memberDeleted').is(':checked')
            };

            fetch(`/admin/member/` + memberId, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(memberData)
            })
            .then(response => {
                if (response.ok) {
                    alert('회원 정보가 성공적으로 업데이트되었습니다.');
                    $('#memberDetailModal').modal('hide');
                    location.reload(); // 페이지 새로고침하여 변경된 데이터 반영
                } else {
                    alert('회원 정보 업데이트에 실패했습니다.');
                }
            })
            .catch(error => console.error('Error updating member:', error));
        });

        // 회원 삭제 버튼 클릭 시
        $('#deleteMemberBtn').on('click', function() {
            if (confirm('정말로 이 회원을 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
                const memberId = $('#memberId').val();
                fetch(`/admin/member/reject/` + memberId, {

                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    if (response.ok) {
                        alert('회원이 성공적으로 삭제되었습니다.');
                        $('#memberDetailModal').modal('hide');
                        location.reload();
                    } else {
                        alert('회원 삭제에 실패했습니다.');
                    }
                })
                .catch(error => console.error('Error deleting member:', error));
            }
        });

        // 비밀번호 초기화 버튼 클릭 시
        $('#resetPasswordBtn').on('click', function() {
            if (confirm('정말로 이 회원의 비밀번호를 초기화하시겠습니까? 초기화된 비밀번호는 \'password\' 입니다.')) {
                const memberId = $('#memberId').val();
                fetch(`/admin/member/reset-password/` + memberId, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    if (response.ok) {
                        alert('비밀번호가 성공적으로 초기화되었습니다.');
                        $('#memberDetailModal').modal('hide');
                    } else {
                        alert('비밀번호 초기화에 실패했습니다.');
                    }
                })
                .catch(error => console.error('Error resetting password:', error));
            }
        });
    });
</script>
</#macro>
