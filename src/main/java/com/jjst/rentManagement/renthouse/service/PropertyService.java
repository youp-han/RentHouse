package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperties();
    void saveProperty(Property property) throws Exception;
    Property getPropertyById(Long id);
    void updateProperty(Property property) throws Exception;
    void deleteProperty(Long id) throws Exception;
    Unit getUnitById(Long id);
    void saveUnit(Unit unit) throws Exception;
    void updateUnit(Unit unit) throws Exception;
    void deleteUnit(Long id) throws Exception;
    List<Unit> getUnitsByPropertyId(long propertyId);
    List<Unit> getAvailableUnitsByPropertyId(Long propertyId);
    List<Unit> getAllUnits();

    Property saveProperty(com.jjst.rentManagement.renthouse.dto.PropertyDto propertyDto);

    Unit saveUnit(com.jjst.rentManagement.renthouse.dto.UnitDto unitDto);
}
