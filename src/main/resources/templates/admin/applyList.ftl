<#include "../common/layout.ftl">
<#import "/spring.ftl" as spring/>
<#macro content>

    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Member List</h1>
        </div>

        <!-- Member List Table -->
        <div class="row">
            <div class="col-lg-12">
                <table id="memberListTable" class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Role</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list memberList as member>
                        <tr>
                            <td>${member.name}</td>
                            <td>${member.email}</td>
                            <td>${member.phone}</td>
                            <td>${member.role}</td>
                            <td>
                                <#if member.role =="USER">
                                    <button class="btn btn-primary tenancy-btn" data-id="${member.id!}">Tenancy</button>
                                    <button class="btn btn-primary user-del-btn" data-id="${member.email!}">DEL</button>
                                </#if>
                                <#if member.role =="ADMIN">
                                    <button class="btn btn-primary admin-ok-btn" data-id="${member.email!}">OK</button>
                                    <button class="btn btn-primary user-del-btn" data-id="${member.email!}">DEL</button>
                                </#if>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>

            </div>
        </div>

    <div class="modal fade" id="tenancyModal" tabindex="-1" role="dialog" aria-labelledby="tenancyModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="tenancyModalLabel">Add Tenancy</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="tenancyForm">
                        <input type="hidden" id="memberId" name="memberId">
                        <div class="form-group">
                            <label for="propertySelect">Select Property:</label>
                            <select id="propertySelect" class="form-control" required>
                                <#list propertyList as property>
                                    <option value="0">선택</option>
                                    <option value="${property.propertyId}">${property.nickname}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="unitSelect">Select Unit:</label>
                            <select id="unitSelect" class="form-control" required></select>
                        </div>
                        <div class="form-group">
                            <label for="startDate">Start Date:</label>
                            <input type="date" id="startDate" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="endDate">End Date:</label>
                            <input type="date" id="endDate" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="deposit">Deposit:</label>
                            <input type="number" id="deposit" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="monthlyRent">Monthly Rent:</label>
                            <input type="number" id="monthlyRent" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="contractNotes">Contract Notes:</label>
                            <textarea id="contractNotes" class="form-control"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="membershipType">Membership Type:</label>
                            <select id="membershipType" class="form-control" required>
                                <option value="MONTHLY">Monthly (월세)</option>
                                <option value="YEARLY">Yearly (연세)</option>
                                <option value="JEONSE">Jeonse (전세)</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Save Tenancy</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            // Initialize DataTable
            const memberTable = $('#memberListTable').DataTable();

            // Show Tenancy Modal
            $(document).on('click', '.tenancy-btn', function() {
                const memberId = $(this).data('id').replace(/,/g, '');
                $('#memberId').val(memberId);
                $('#tenancyModal').modal('show');

                // Set default values
                const today = new Date().toISOString().split('T')[0];
                $('#startDate').val(today);
                const twoYearsLater = new Date();
                twoYearsLater.setFullYear(twoYearsLater.getFullYear() + 2);
                $('#endDate').val(twoYearsLater.toISOString().split('T')[0]);
                $('#deposit').val(0);
                $('#monthlyRent').val(0);
                $('#contractNotes').val('');
            });

            // save Admin Approved
            $(document).on('click', '.admin-ok-btn', function() {
                const memberEmail = $(this).data('id');
                $.ajax({
                    url: '/member/save',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        email: memberEmail,
                        role : "ADMIN",
                        approved : true,
                        isNew : false
                    }),
                    success: function(response) {
                        $('#modalMessage').text('Admin Saved successfully');
                        $('#statusModal').modal('show');
                        $('#tenancyModal').modal('hide');
                        location.href="/admin/newMembers";
                    },
                    error: function(xhr, status, error) {
                        $('#modalMessage').text('Failed to Save Admin');
                        $('#statusModal').modal('show');
                    }
                });

            });
            // delete Admin Approved
            $(document).on('click', '.user-del-btn', function() {
                const memberEmail = $(this).data('id');
                $.ajax({
                    url: '/member/save',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        email: memberEmail,
                        deleted : true
                    }),
                    success: function(response) {
                        $('#modalMessage').text('Admin Saved successfully');
                        $('#statusModal').modal('show');
                        $('#tenancyModal').modal('hide');
                        location.href="/admin/newMembers";
                    },
                    error: function(xhr, status, error) {
                        $('#modalMessage').text('Failed to Save Admin');
                        $('#statusModal').modal('show');
                    }
                });

            });

            // Handle form submission
            $('#tenancyForm').on('submit', function(event) {
                event.preventDefault();

                console.log('memberId: ' + $('#memberId').val())
                console.log('unitSelect: ' + $('#unitSelect').val())
                console.log('startDate: ' + $('#startDate').val())
                console.log('endDate: ' + $('#endDate').val())
                console.log('deposit: ' + $('#deposit').val())
                console.log('monthlyRent: ' + $('#monthlyRent').val())
                console.log('contractNotes: ' + $('#contractNotes').val())
                console.log('membershipType: ' + $('#membershipType').val())

                $.ajax({
                    url: '/admin/tenancy/save',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        memberId: $('#memberId').val(),
                        unitId: $('#unitSelect').val(),
                        startDate: $('#startDate').val(),
                        endDate: $('#endDate').val(),
                        deposit: $('#deposit').val(),
                        monthlyRent: $('#monthlyRent').val(),
                        contractNotes: $('#contractNotes').val(),
                        membershipType: $('#membershipType').val(),
                        deleted : false
                    }),
                    success: function(response) {
                        if (response.status === 'success') {
                            $('#modalMessage').text('Tenancy created successfully');
                        } else {
                            $('#modalMessage').text('Failed to create tenancy');
                        }
                        $('#statusModal').modal('show');
                        $('#tenancyModal').modal('hide');
                        //memberTable.ajax.reload(); // Reload DataTable
                    },
                    error: function(xhr, status, error) {
                        $('#modalMessage').text('Failed to create tenancy');
                        $('#statusModal').modal('show');
                    }
                });
            });

            // Fetch units based on selected property
            $('#propertySelect').change(function(event) {
                event.preventDefault();

                const propertyId = $(this).val();
                $('#unitSelect').empty(); // Clear previous options

                if(propertyId > 0) {
                    $.ajax({
                        url: `/admin/property/units/` + propertyId,
                        type: 'GET',
                        contentType: 'application/json',
                        success: function(data) {
                            data.forEach(unit => {
                                $('#unitSelect').append(new Option(unit.unitNumber, unit.id));
                            });
                        },
                        error: function(xhr, status, error) {
                            $('#modalMessage').text('Failed to fetch units');
                            $('#statusModal').modal('show');
                        }
                    });
                }
            });

            // Update EndDate based on StartDate change
            $('#startDate').change(function() {
                const startDate = new Date($(this).val());
                const twoYearsLater = new Date(startDate);
                twoYearsLater.setFullYear(startDate.getFullYear() + 2);
                $('#endDate').val(twoYearsLater.toISOString().split('T')[0]);
            });
        });
    </script>

</#macro>