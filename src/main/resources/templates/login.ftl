

<#include "common/layout.ftl">
<#import "/spring.ftl" as spring/>
<#macro content>

    <div class="container-fluid">

        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Login</h1>
        </div>
        <!-- Content Row -->
        <div class="row">

            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-warning shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                    Pending Requests</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-comments fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </div>

        <div class="row">
            <form action="${springMacroRequestContext.contextPath}/login" method="post">
                <div>
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="buttons">
                    <button type="submit">Login</button>
                    <a href="${springMacroRequestContext.contextPath}/member/join">Join</a>
                </div>
            </form>
            <a href="${springMacroRequestContext.contextPath}/oauth2/authorization/naver">Login with Naver</a>
        </div>



    </div>

    <!-- /.container-fluid -->
</#macro>