<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
</head>
<body>
<h1>List of Property</h1>
<#if properties?size == 0>
    <p>No properties found. <a href="/admin/property/register">Register Property</a></p>
<#else>
    <ul>
        <#list properties as property>
            <li>${property.address}</li>
            <ul>
                <#if property.units?size == 0>
                    <li>No units found. <a href="/admin/property/unit/register?propertyId=${property.id?replace(',', '')}">Register Unit</a></li>



                <#else>
                    <#list property.units as unit>
                        <li>Unit: ${unit.unitNumber!''}</li>
                        <ul>
                            <#if unit.getUnitAttributes??>
                                <#if unit.getUnitAttributes?size == 0>
                                    <li>No rooms found. <a href="/admin/property/unit/unitAttribute/register?unitId=${unit.id?replace(',', '')}">Register Room</a></li>
                                <#else>
                                    <#list unit.getUnitAttributes as unitAttribute>
                                        <li>Room: ${unitAttribute.type}, Size: ${unitAttribute.size} sqm, Features: ${unitAttribute.features}</li>
                                    </#list>
                                </#if>
                            <#else>
                                <li>No rooms found. <a href="/admin/property/unit/unitAttribute/register?unitId=${unit.id?replace(',', '')}">Register Room</a></li>
                            </#if>
                        </ul>
                    </#list>
                </#if>
            </ul>
            <li><a href="/admin/property/unit/register?propertyId=${property.id?replace(',', '')}">Register Unit</a></li>
            <li><a href="/admin/unitInfo?propertyId=${property.id?replace(',', '')}">Unit Information (rooms)</a></li>
        </#list>
    </ul>
</#if>

<h2>Property Unit List with Renting Status</h2>
<ul>
    <#list properties as property>
        <#list property.units as unit>
            <li>${unit.unitNumber!''} - ${unit.rentStatus!''}</li>
        </#list>
    </#list>
</ul>

<h2>Property Rent Profit</h2>
<ul>
    <#list properties as property>
        <#list property.units as unit>
            <li>${unit.unitNumber!''} - Estimated Rent: ${unit.estimatedRent!''}</li>
        </#list>
    </#list>
</ul>

<h2>Q/A</h2>
<p>For any questions, please contact the admin.</p>
</body>
</html>