<#include "common/layout.ftl">
<#import "/spring.ftl" as spring/>

<#macro content>
    <div class="container-fluid">

        <#-- Check if user is logged in (by checking if 'name' attribute exists) -->
        <#if name??>
            <#-- Logged-in user content -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-0 text-gray-800">환영합니다, ${name}님!</h1>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <p class="mb-4">현재 역할: <strong>${role}</strong></p>
                    
                    <h5 class="mb-3">빠른 시작</h5>
                    <a href="/admin/dashboard" class="btn btn-primary btn-icon-split mr-2 mb-2">
                        <span class="icon text-white-50"><i class="fas fa-tachometer-alt"></i></span>
                        <span class="text">대시보드로 이동</span>
                    </a>
                    <a href="/property/register" class="btn btn-success btn-icon-split mr-2 mb-2">
                        <span class="icon text-white-50"><i class="fas fa-building"></i></span>
                        <span class="text">부동산 관리</span>
                    </a>
                    <a href="/leases/register" class="btn btn-info btn-icon-split mr-2 mb-2">
                        <span class="icon text-white-50"><i class="fas fa-file-contract"></i></span>
                        <span class="text">임대 계약 관리</span>
                    </a>
                </div>
            </div>

        <#else>
            <#-- Not logged-in user content -->
            <div class="text-center" style="padding: 80px 0;">
                <h1 class="h1 mb-3 text-gray-800">RentHouse 관리 시스템</h1>
                <p class="lead text-gray-600 mb-4">임대인을 위한 간편한 임대 관리 솔루션</p>
                <a href="/login" class="btn btn-primary btn-lg mr-2">로그인</a>
                <a href="/member/join" class="btn btn-secondary btn-lg">회원가입</a>
            </div>
        </#if>

    </div>
</#macro>
