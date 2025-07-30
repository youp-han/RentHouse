package com.jjst.rentManagement.renthouse.restController;

import com.jjst.rentManagement.renthouse.dto.PropertyDto;
import com.jjst.rentManagement.renthouse.dto.UnitDto;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PropertyRestController {

    @Autowired
    private PropertyService propertyService;

    // Update Property
    @PutMapping("/properties/{id}")
    public ResponseEntity<Map<String, Object>> updateProperty(@PathVariable Long id, @RequestBody PropertyDto propertyDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Property existingProperty = propertyService.getPropertyById(id);
            if (existingProperty == null) {
                response.put("status", "error");
                response.put("message", "부동산을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            existingProperty.setName(propertyDto.getName());
            existingProperty.setAddress(propertyDto.getAddress());
            // Assuming PropertyDto.type is String and Property.type is Enum
            existingProperty.setType(com.jjst.rentManagement.renthouse.module.common.enums.PropertyType.valueOf(propertyDto.getType()));
            existingProperty.setTotalFloors(propertyDto.getTotalFloors());

            propertyService.updateProperty(existingProperty);
            response.put("status", "success");
            response.put("message", "부동산 정보가 성공적으로 업데이트되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "부동산 정보 업데이트 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Delete Property
    @DeleteMapping("/properties/{id}")
    public ResponseEntity<Map<String, Object>> deleteProperty(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            propertyService.deleteProperty(id);
            response.put("status", "success");
            response.put("message", "부동산이 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "부동산 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update Unit
    @PutMapping("/units/{id}")
    public ResponseEntity<Map<String, Object>> updateUnit(@PathVariable Long id, @RequestBody UnitDto unitDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Unit existingUnit = propertyService.getUnitById(id);
            if (existingUnit == null) {
                response.put("status", "error");
                response.put("message", "유닛을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            existingUnit.setUnitNumber(unitDto.getUnitNumber());
            existingUnit.setRentStatus(unitDto.isRentStatus());
            existingUnit.setSize_meter(unitDto.getSize_meter());
            existingUnit.setSize_korea(unitDto.getSize_korea());
            existingUnit.setUseType(unitDto.getUseType());
            existingUnit.setDescription(unitDto.getDescription());

            propertyService.updateUnit(existingUnit);
            response.put("status", "success");
            response.put("message", "유닛 정보가 성공적으로 업데이트되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "유닛 정보 업데이트 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Delete Unit
    @DeleteMapping("/units/{id}")
    public ResponseEntity<Map<String, Object>> deleteUnit(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            propertyService.deleteUnit(id);
            response.put("status", "success");
            response.put("message", "유닛이 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "유닛 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}