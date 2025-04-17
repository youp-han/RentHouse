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
                                                <li>No units found.
                                                    <a href="/admin/property/unit/register?propertyId=${property.id?replace(',', '')}"
                                                       class="alert-link">Register Unit</a></li>
                                            <#else>
                                                <#list property.units as unit>
                                                    <li>
                                                        <a href="javascript:void(0);" class="toggle-unit"
                                                           data-unit-id="${unit.id}">Unit: ${unit.unitNumber!''}</a>
                                                        <ul id="unitAttributes-${unit.id}" style="display: none;">
                                                            <#assign unitAttributes = unit.unitAttributes>
                                                            <#if unitAttributes?size == 0>
                                                                <li>No rooms found.
                                                                    <a href="/admin/property/unit/unitAttribute/register?unitId=${unit.id?replace(',', '')}"
                                                                       class="alert-link">Register Room</a></li>
                                                            <#else>
                                                                <#list unitAttributes as unitAttribute>
                                                                    <li>Room: ${unitAttribute.featureKey},
                                                                        Value: ${unitAttribute.featureValue}</li>
                                                                </#list>
                                                            </#if>
                                                        </ul>
                                                    </li>
                                                </#list>
                                            </#if>
                                        </ul>
                                    </td>
                                    <td>
<#--                                        <a href="/admin/property/unit/register?propertyId=${property.id?replace(',', '')}"-->
<#--                                           class="btn btn-primary btn-sm">Register Unit</a>-->
                                        <a href="javascript:void(0);" class="btn btn-primary btn-sm register-unit-btn"
                                           data-property-id="${property.id}" data-property-address="${property.address}">Register Unit</a>

                                        <a href="/admin/unitInfo?propertyId=${property.id?replace(',', '')}"
                                           class="btn btn-info btn-sm">Unit Information (rooms)</a>
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


    <!-- Modal -->
    <div class="modal fade" id="unitRegisterModal" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Register Unit</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="registerUnitForm" action="/admin/property/unit/save" method="post">
                        <input type="hidden" id="propertyId" name="propertyId" value="">
                        <p>Property:</p>  <label id="propertyAddress" name="propertyAddress" value="" />
                        <label for="unitNumber">Unit Number:</label>
                        <input type="text" id="unitNumber" name="unitNumber" required>
                        <button type="submit" class="btn btn-primary">Save Unit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script>
        $(document).ready(function() {
            $('#propertyTable').DataTable();

            $('.toggle-unit').on('click', function() {
                var unitId = $(this).data('unit-id');
                console.log('Toggling attributes for unit:', unitId); // Debugging line
                $('#unitAttributes-' + unitId).toggle();
            });

            // Register Unit 버튼 클릭 이벤트
            $('.register-unit-btn').on('click', function () {
                var propertyId = $(this).data('property-id'); // data-property-id 읽기
                var address = $(this).data('property-address'); // data-property-id 읽기
                console.log('Clicked Property ID:', propertyId); // 디버깅용
                console.log('Clicked Property address:', address); // 디버깅용

                // 숨겨진 필드에 propertyId 값 설정
                $('#propertyId').val(propertyId);
                $('#propertyAddress').val(address);

                // 모달 표시
                $('#unitRegisterModal').modal('show');
            });

        });
    </script>
</#macro>