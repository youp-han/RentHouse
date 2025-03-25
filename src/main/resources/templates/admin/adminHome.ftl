<#include "../common/layout.ftl">
<#import "/spring.ftl" as spring/>
<#macro content>
    <!-- Begin Page Content -->
    <div class="container-fluid">
        <h1 class="h3 mb-4 text-gray-800">List of Property</h1>
        <#if properties?size == 0>
            <div class="alert alert-warning" role="alert">
                No properties found. <a href="/admin/property/register" class="alert-link">Register Property</a>
            </div>
        <#else>
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Properties</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table id="propertyTable" class="table table-bordered" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Address</th>
                                <th>Units</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list properties as property>
                                <tr>
                                    <td>${property.address}</td>
                                    <td>
                                        <ul>
                                            <#if property.units?size == 0>
                                                <li>No units found. <a href="/admin/property/unit/register?propertyId=${property.id?replace(',', '')}" class="alert-link">Register Unit</a></li>
                                            <#else>
                                                <#list property.units as unit>
                                                    <li>
                                                        <a href="javascript:void(0);" class="toggle-unit" data-unit-id="${unit.id}">Unit: ${unit.unitNumber!''}</a>
                                                        <ul id="unitAttributes-${unit.id}" style="display: none;">
                                                            <#assign unitAttributes = unit.unitAttributes>
                                                            <#if unitAttributes?size == 0>
                                                                <li>No rooms found. <a href="/admin/property/unit/unitAttribute/register?unitId=${unit.id?replace(',', '')}" class="alert-link">Register Room</a></li>
                                                            <#else>
                                                                <#list unitAttributes as unitAttribute>
                                                                    <li>Room: ${unitAttribute.featureKey}, Value: ${unitAttribute.featureValue}</li>
                                                                </#list>
                                                            </#if>
                                                        </ul>
                                                    </li>
                                                </#list>
                                            </#if>
                                        </ul>
                                    </td>
                                    <td>
                                        <a href="/admin/property/unit/register?propertyId=${property.id?replace(',', '')}" class="btn btn-primary btn-sm">Register Unit</a>
                                        <a href="/admin/unitInfo?propertyId=${property.id?replace(',', '')}" class="btn btn-info btn-sm">Unit Information (rooms)</a>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </#if>
    </div>
    <!-- /.container-fluid -->

    <script>
        $(document).ready(function() {
            $('#propertyTable').DataTable();

            $('.toggle-unit').on('click', function() {
                var unitId = $(this).data('unit-id');
                console.log('Toggling attributes for unit:', unitId); // Debugging line
                $('#unitAttributes-' + unitId).toggle();
            });
        });
    </script>
</#macro>