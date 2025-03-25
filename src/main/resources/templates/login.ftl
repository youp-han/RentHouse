<#include "common/layout.ftl">
<#import "/spring.ftl" as spring/>
<#macro content>

    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">로그인</h1>
        </div>

        <!-- Login Card -->
        <div class="row justify-content-center">
            <div class="col-xl-6 col-lg-8 col-md-10">
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Welcome Back!</h6>
                    </div>

                    <div class="card-body">
                        <form id="loginForm">
                            <div class="form-group">
                                <label for="email">email:</label>
                                <input type="text" id="email" name="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input type="password" id="password" name="password" class="form-control" required>
                            </div>
                            <div class="d-flex justify-content-between mt-3">
                                <button type="submit" class="btn btn-primary">Login</button>
                                <a href="${springMacroRequestContext.contextPath}/member/join" class="btn btn-secondary">Join</a>
                            </div>
                        </form>
                        <div class="text-center mt-3">
                            <a href="${springMacroRequestContext.contextPath}/oauth2/authorization/naver" class="btn btn-success">Login with Naver</a>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>

    <script>
        $(document).ready(function() {
            $('#loginForm').on('submit', function(event) {
                event.preventDefault(); // Prevent default form submission
                console.log($('#email').val());
                console.log($('#password').val());

                $.ajax({
                    url: '/member/authenticate',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        email: $('#email').val(),
                        password: $('#password').val()
                    }),
                    success: function(response) {
                        if (response === "success") {
                            window.location.href = "/"; // Redirect to home page
                        } else {
                            alert("Invalid credentials. Please try again.");
                        }
                    },
                    error: function() {
                        alert("An error occurred during login. Please try again.");
                    }
                });
            });
        });
    </script>



</#macro>
