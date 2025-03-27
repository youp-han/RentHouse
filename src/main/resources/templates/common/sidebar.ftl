<#macro sidebar>
    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Rent House<sup>2</sup></div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="/">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>HOME</span></a>
    </li>

    <#if role?? && role=="ADMIN">

    <!-- Divider -->
    <hr class="sidebar-divider">

    <!-- Heading -->
    <div class="sidebar-heading">
        Admin
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOne"
           aria-expanded="true" aria-controls="collapseOne">
            <i class="fas fa-fw fa-cog"></i>
            <span>Management</span>
        </a>
        <div id="collapseOne" class="collapse" aria-labelledby="headingone" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">menu:</h6>
                <a class="collapse-item" href="/admin/home">Admin Home</a>
                <a class="collapse-item" href="/admin/tenantsList">Tenants</a>
                <a class="collapse-item" href="/admin/newMembers">New Members</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
           aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-fw fa-cog"></i>
            <span>Properties</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingone" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">menu:</h6>
                <a class="collapse-item" href="/admin/propertyList">Properties</a>
            </div>
        </div>
    </li>


    </#if>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">




    <!-- Heading -->
    <div class="sidebar-heading">
        USER
    </div>

    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseThree"
           aria-expanded="true" aria-controls="collapseThree">
            <i class="fas fa-fw fa-cog"></i>
            <span>user menu</span>
        </a>
        <div id="collapseThree" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">menu:</h6>
                <a class="collapse-item" href="/login">Log-in</a>
                <#if user??>
                <a class="collapse-item" href="/member/settings">Settings</a>

                </#if>
                <a class="collapse-item" href="/member/detail">Cards</a>
            </div>
        </div>
    </li>


    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>



</#macro>