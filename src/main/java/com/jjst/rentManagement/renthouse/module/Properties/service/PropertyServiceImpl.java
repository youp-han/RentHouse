package com.jjst.rentManagement.renthouse.module.Properties.service;

import com.jjst.rentManagement.renthouse.service.PropertyService;
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

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Property not found"));
    }

    public List<Unit> getUnitsByPropertyId(long propertyId){

        return unitRepository.findByPropertyId(propertyId);
    }

    public Unit getUnitById(Long id) {
        return unitRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Unit not found"));
    }

    public void saveProperty(Property property) throws Exception {
        try{
            propertyRepository.save(property);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }

    public void saveUnit(Unit unit) throws Exception{
        try{
            unitRepository.save(unit);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }


}
