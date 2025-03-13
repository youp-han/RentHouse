package com.jjst.rentManagement.renthouse.module.Properties.service;

import com.jjst.rentManagement.renthouse.Service.PropertyService;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.Properties.repository.PropertyRepository;
import com.jjst.rentManagement.renthouse.module.Properties.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UnitRepository unitRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }


    public void saveProperty(Property property) {
        propertyRepository.save(property);
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Property not found"));
    }

    public List<Unit> getAllUnits(){return unitRepository.findAll();}

    public Unit getUnitById(Long id) {
        return unitRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Unit not found"));
    }

    public void saveUnit(Unit unit) {
        unitRepository.save(unit);
    }


}
