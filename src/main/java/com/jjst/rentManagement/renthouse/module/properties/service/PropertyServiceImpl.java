package com.jjst.rentManagement.renthouse.module.properties.service;

import com.jjst.rentManagement.renthouse.dto.PropertyDto;
import com.jjst.rentManagement.renthouse.dto.UnitDto;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.properties.repository.PropertyRepository;
import com.jjst.rentManagement.renthouse.module.properties.repository.UnitRepository;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private EntityConverter entityConverter;

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

    @Override
    public List<Unit> getAvailableUnitsByPropertyId(Long propertyId) {
        return unitRepository.findByPropertyIdAndRentStatusIsFalse(propertyId);
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

    public Property saveProperty(PropertyDto propertyDto) {
        Property property = entityConverter.convertToEntity(propertyDto, Property.class);
        return propertyRepository.save(property);
    }

    public Unit saveUnit(UnitDto unitDto) {
        Unit unit = entityConverter.convertToEntity(unitDto, Unit.class);
        return unitRepository.save(unit);
    }

    public void saveUnit(Unit unit) throws Exception{
        try{
            unitRepository.save(unit);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void updateProperty(Property property) throws Exception {
        Property existingProperty = propertyRepository.findById(property.getId())
                .orElseThrow(() -> new RuntimeException("Property not found for id: " + property.getId()));

        existingProperty.setName(property.getName());
        existingProperty.setAddress(property.getAddress());
        existingProperty.setType(property.getType());
        existingProperty.setTotalFloors(property.getTotalFloors());

        try {
            propertyRepository.save(existingProperty);
        } catch (Exception e) {
            throw new Exception("Error updating property", e);
        }
    }

    @Override
    public void deleteProperty(Long id) throws Exception {
        if (!propertyRepository.existsById(id)) {
            throw new RuntimeException("Property not found for id: " + id);
        }
        propertyRepository.deleteById(id);
    }

    @Override
    public void updateUnit(Unit unit) throws Exception {
        Unit existingUnit = unitRepository.findById(unit.getId())
                .orElseThrow(() -> new RuntimeException("Unit not found for id: " + unit.getId()));

        existingUnit.setUnitNumber(unit.getUnitNumber());
        existingUnit.setRentStatus(unit.getRentStatus());
        existingUnit.setSize_meter(unit.getSize_meter());
        existingUnit.setSize_korea(unit.getSize_korea());
        existingUnit.setUseType(unit.getUseType());
        existingUnit.setDescription(unit.getDescription());

        try {
            unitRepository.save(existingUnit);
        } catch (Exception e) {
            throw new Exception("Error updating unit", e);
        }
    }

    @Override
    public void deleteUnit(Long id) throws Exception {
        if (!unitRepository.existsById(id)) {
            throw new RuntimeException("Unit not found for id: " + id);
        }
        unitRepository.deleteById(id);
    }

    @Override
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

}
