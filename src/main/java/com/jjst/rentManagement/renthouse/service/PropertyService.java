package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.module.Properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;

import java.util.List;

public interface PropertyService {

    List<Property> getAllProperties();
    void saveProperty(Property property);
    Property getPropertyById(Long id);
    Unit getUnitById(Long id);
    void saveUnit(Unit unit);
    List<Unit> getUnitsByPropertyId(long propertyId);

}
