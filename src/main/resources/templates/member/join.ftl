<#include "../common/layout.ftl">
<#import "/spring.ftl" as spring/>
<#macro content>

    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">회원 가입</h1>
        </div>

        <!-- Join Form Card -->
        <div class="row justify-content-center">
            <div class="col-xl-8 col-lg-10 col-md-12">
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">회원 가입</h6>
                    </div>
                    <div class="card-body">
                        <form id="joinForm">
                            <!-- Name -->
                            <div class="form-group">
                                <label for="name">이름:</label>
                                <input type="text" id="name" name="name" class="form-control" required>
                            </div>

                            <!-- rentPayer -->
                            <div class="form-group">
                                <label for="rentPayer">송금인:</label>
                                <input type="text" id="rentPayer" name="rentPayer" class="form-control" required>
                            </div>

                            <!-- Phone -->
                            <div class="form-group">
                                <label for="phone">전화번호:</label>
                                <input type="text" id="phone" name="phone" class="form-control" required>
                            </div>

                            <!-- Email -->
                            <div class="form-group">
                                <label for="email">이메일:</label>
                                <input type="email" id="email" name="email" class="form-control" required>
                                <button type="button" id="sendVerificationCode" class="btn btn-sm btn-info">인증번호 전송</button>
                            </div>

                            <!-- Password -->
                            <div class="form-group">
                                <label for="password">암호:</label>
                                <input type="password" id="password" name="password" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">암호 확인:</label>
                                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
                            </div>

                            <!-- Role -->
                            <div class="form-group">
                                <label for="role">역할:</label>
                                <select id="role" name="role" class="form-control" required>
                                    <option value="USER" selected>사용자</option>
                                    <option value="ADMIN">관리자</option>
                                </select>
                            </div>

                            <!-- SNS Type -->
                            <div class="form-group">
                                <label for="snsType">SNS 유형:</label>
                                <select id="snsType" name="snsType" class="form-control">
                                    <option value="0">없음</option>
                                    <option value="1">네이버</option>
                                    <option value="2">카카오</option>
                                    <option value="3">구글</option>
                                </select>
                            </div>

                            <!-- SNS Fields -->
                            <div id="snsFields" class="form-group" style="display:none;">
                                <label for="snsId">SNS ID:</label>
                                <input type="text" id="snsId" name="snsId" class="form-control">
                            </div>

                            <!-- Submit -->
                            <div class="d-flex justify-content-end">
                                <button type="submit" class="btn btn-primary">가입</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Modal -->
    <div class="modal fade" id="statusModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">알림</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body" id="modalMessage"></div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery Script -->
    <script>
        $(document).ready(function() {
            // Match Passwords Validation and save
            $('#joinForm').on('submit', function(event) {
                event.preventDefault(); // Prevent the default form submission
                const formData = $(this).serialize(); // Serialize form data
                const password = $('#password').val();
                const confirmPassword = $('#confirmPassword').val();

                if (password !== confirmPassword) {
                    event.preventDefault();
                    alert("암호가 일치하지 않습니다. 다시 입력해주세요.");
                }else{
                    $.ajax({
                        url: '/member/save',
                        type: 'POST',
                        contentType: 'application/json', // Specify JSON
                        data: JSON.stringify({
                            name: $('#name').val(),
                            rentPayer: $('#rentPayer').val(),
                            phone: $('#phone').val(),
                            email: $('#email').val(),
                            password: $('#password').val(),
                            role: $('#role').val(),
                            snsType: $('#snsType').val(),
                            snsId: $('#snsId').val()
                        }),
                        success: function(response) {
                            $('#modalMessage').text("회원 가입이 완료되었습니다!");
                            $('#statusModal').modal('show');
                            location.href ='/login'
                        },
                        error: function(xhr, status, error) {
                            $('#modalMessage').text("회원 가입에 실패했습니다. 다시 시도해주세요.");
                            $('#statusModal').modal('show');
                        }
                    });



                }
            });


            //SNS 유형 및 SNS ID 처리
            $('#snsType').change(function() {
                const snsType = $(this).val();
                if (snsType === "0") {
                    $('#snsFields').hide();
                    $('#snsId').val(''); // 필드 초기화
                } else {
                    $('#snsFields').show();
                }
            });


            //email 중복확인
            $('#eMail').on('blur', function() {
                const email = $(this).val();
                // if(email.trim().length > 3) {
                //     $.get('/member/check-email', {email: email}, function (isExists) {
                //         if (isExists) {
                //             $('#eMail').addClass('is-invalid');
                //             alert('이미 존재하는 이메일입니다. 다른 이메일을 사용해주세요.');
                //         } else {
                //             alert(isExists);
                //             $('#eMail').removeClass('is-invalid').addClass('is-valid');
                //         }
                //     });
                // }
            });


            // $('#confirmPassword').on('keyup', function() {
            //     const password = $('#password').val();
            //     const confirmPassword = $(this).val();
            //     if (password !== confirmPassword) {
            //         $(this).addClass('is-invalid');
            //     } else {
            //         $(this).removeClass('is-invalid').addClass('is-valid');
            //     }
            // });
        });
    </script>

</#macro>
