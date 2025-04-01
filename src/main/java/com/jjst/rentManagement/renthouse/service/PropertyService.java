package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.module.Properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperties();
    void saveProperty(Property property) throws Exception;
    Property getPropertyById(Long id);
    Unit getUnitById(Long id);
    void saveUnit(Unit unit) throws Exception;
    List<Unit> getUnitsByPropertyId(long propertyId);

}
