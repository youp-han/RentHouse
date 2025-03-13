<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#snsType').change(function() {
                if ($(this).val() === "0") {
                    $('#snsFields').hide();
                } else {
                    $('#snsFields').show();
                }
            });

            $('#sendVerificationCode').click(function() {
                // Simulate sending verification code
                alert('인증번호가 전송되었습니다.');
                $('#verificationCodeSection').show();
            });

            $('#verifyCode').click(function() {
                // Simulate verifying the code
                var enteredCode = $('#verificationCode').val();
                if (enteredCode === '123456') { // Replace with actual verification logic
                    alert('인증되었습니다.');
                    $('#verificationConfirmed').prop('checked', true);
                } else {
                    alert('인증번호가 올바르지 않습니다.');
                }
            });
        });
    </script>
</head>
<body>
<h1>회원 가입</h1>
<form action="/member/join" method="post">
    <label for="name">이름:</label>
    <input type="text" id="name" name="name" required><br><br>

    <label for="phone">전화번호:</label>
    <input type="text" id="phone" name="phone" required><br><br>

    <label for="eMail">이메일:</label>
    <input type="email" id="eMail" name="eMail" required>
    <button type="button" id="sendVerificationCode">인증번호 전송</button><br><br>

    <div id="verificationCodeSection" style="display:none;">
        <label for="verificationCode">인증번호:</label>
        <input type="text" id="verificationCode" name="verificationCode">
        <button type="button" id="verifyCode">번호 확인</button>
        <input type="checkbox" id="verificationConfirmed" name="verificationConfirmed" disabled> 인증 확인<br><br>
    </div>

    <label for="role">역할:</label>
    <select id="role" name="role" required>
        <option value="user" selected="true">사용자</option>
        <option value="admin">관리자</option>
    </select><br><br>

    <label for="snsType">SNS 유형:</label>
    <select id="snsType" name="snsType">
        <option value="0">없음</option>
        <option value="1">네이버</option>
<#--        <option value="2">카카오</option>-->
<#--        <option value="3">구글</option>-->
    </select><br><br>

    <div id="snsFields" style="display:none;">
        <label for="snsId">SNS ID:</label>
        <input type="text" id="snsId" name="snsId"><br><br>
    </div>

    <button type="submit">가입</button>
</form>
</body>
</html>