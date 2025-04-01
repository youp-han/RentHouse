<#include "../common/layout.ftl">
<#import "/spring.ftl" as spring/>
<#macro content>

    <div class="container-fluid">
        <h1 class="mt-4 mb-4">Tenants List</h1>
        <table id="tenantsTable" class="table table-striped table-bordered" style="width:100%">
            <thead>
            <tr>
                <th>Member ID</th>
                <th>Unit ID</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Deposit</th>
                <th>Monthly Rent</th>
                <th>Contract Notes</th>
                <th>Membership Type</th>
            </tr>
            </thead>
            <tbody>
            <#list tenantList as tenancy>
                <tr>
                    <td>${tenancy.memberId}</td>
                    <td>${tenancy.unitId}</td>
                    <td>${tenancy.startDate}</td>
                    <td>${tenancy.endDate}</td>
                    <td>${tenancy.deposit}</td>
                    <td>${tenancy.monthlyRent}</td>
                    <td>${tenancy.contractNotes}</td>
                    <td>${tenancy.membershipType}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <!-- Scripts -->
    <script>
        $(document).ready(function() {
            $('#tenantsTable').DataTable();
        });
    </script>

</#macro>