package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.dto.PropertyDto;
import com.jjst.rentManagement.renthouse.dto.UnitDto;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ... (기존 import 문 유지)

@Controller
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Value("${system.juso.search.api}")
    private String jusoApiKey;

    //Register Property
    @GetMapping("/register")
    public String registerProperty(Model model){
        model.addAttribute("jusoApiKey", jusoApiKey);
        return "/property/register";
    }

    @PostMapping("/save")
    public String saveProperty(@ModelAttribute PropertyDto propertyDto) {
        try {
            Property property = new Property();
            property.setName(propertyDto.getName());

            String address = propertyDto.getRoadAddress() + " " + propertyDto.getDetailAddress() + " (" + propertyDto.getZipCode() + ")";
            property.setAddress(address);

            property.setType(com.jjst.rentManagement.renthouse.module.common.enums.PropertyType.valueOf(propertyDto.getType()));

            if ("other".equals(propertyDto.getTotalFloorsSelect())) {
                property.setTotalFloors(propertyDto.getTotalFloors());
            } else {
                property.setTotalFloors(Integer.valueOf(propertyDto.getTotalFloorsSelect()));
            }

            if (com.jjst.rentManagement.renthouse.module.common.enums.PropertyType.valueOf(propertyDto.getType()) == com.jjst.rentManagement.renthouse.module.common.enums.PropertyType.WKUp_VILLA) {
                property.setTotalUnits(propertyDto.getTotalUnits());
            } else {
                property.setTotalUnits(1);
            }

            propertyService.saveProperty(property);
            return "redirect:/property/propertyList";
        } catch (Exception e) {
            // Handle error, e.g., add error message to model and return to form
            return "redirect:/property/register";
        }
    }


    @GetMapping("/propertyList")
    public String listProperties(Model model){
        List<Property> propertyList = propertyService.getAllProperties();
        model.addAttribute("properties", propertyList);

        return "/property/propertyList";
    }

    @GetMapping("/detail/{id}")
    public String propertyDetail(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        if (property != null) {
            model.addAttribute("property", property);
            return "property/detail";
        } else {
            return "redirect:/property/propertyList"; // Or handle error
        }
    }



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
            // 주소는 변경하지 않음
            // existingProperty.setAddress(propertyDto.getAddress());
            existingProperty.setType(com.jjst.rentManagement.renthouse.module.common.enums.PropertyType.valueOf(propertyDto.getType()));
            existingProperty.setTotalFloors(propertyDto.getTotalFloors());
            existingProperty.setTotalUnits(propertyDto.getTotalUnits());

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

    // 주소 팝업
    // GET, POST 둘 다 받도록
    @RequestMapping(value = "/jusoPopup", method = {RequestMethod.GET, RequestMethod.POST})
    public String jusoPopup(
            @RequestParam(required = false) String inputYn,
            @RequestParam Map<String,String> params,
            Model model,
            HttpServletRequest request
    ) {
        // 최초 호출인지 검사 (inputYn=Y 면 콜백)

        boolean isCallback = "Y".equals(inputYn);

        model.addAttribute("jusoApiKey", jusoApiKey);
        model.addAttribute("inputYn",       isCallback ? "Y" : "N");

        // API가 주소 검색 후 돌려줄 URL: jusoPopup 자체로, 반드시 inputYn=Y 쿼리 포함
        //String callbackUrl = request.getRequestURL() + "?inputYn=Y";
        String callbackUrl = String.valueOf(request.getRequestURL());
        model.addAttribute("returnUrl", callbackUrl);

        // 콜백일 때만 API 응답 파라미터를 모델에 담아 넘김
        if (isCallback) {
            params.forEach(model::addAttribute);
        }

        return "common/jusoPopup";
    }


//    @GetMapping("/property/unit/detail/{id}")
//    public String unitDetail(@PathVariable Long id, Model model) {
//        Unit unit = propertyService.getUnitById(id);
//        if (unit != null) {
//            model.addAttribute("unit", unit);
//            return "property/unit/detail";
//        } else {
//            return "redirect:/property/propertyList"; // Or handle error
//        }
//    }

    //get an unit data
    @GetMapping("/units/{id}")
    @ResponseBody
    public Unit getUnit(@PathVariable Long id) {
        return propertyService.getUnitById(id);
    }

    //save unit
    @PostMapping("/unit/save")
    public String saveUnit(@RequestBody UnitDto unitDto) {
        try {
            Property property = propertyService.getPropertyById(unitDto.getPropertyId());
            Unit unit = new Unit();
            unit.setProperty(property);
            unit.setUnitNumber(unitDto.getUnitNumber());
            unit.setRentStatus(unitDto.isRentStatus());
            unit.setSize_meter(unitDto.getSize_meter());
            unit.setSize_korea(unitDto.getSize_korea());
            unit.setUseType(unitDto.getUseType());
            unit.setDescription(unitDto.getDescription());
            propertyService.saveUnit(unit);
            return "redirect:/property/detail/" + unitDto.getPropertyId();
        } catch (Exception e) {
            // Handle error
            return "redirect:/property/unit/register?propertyId=" + unitDto.getPropertyId();
        }
    }
    // Update Unit
    @PutMapping("/unit/{id}")
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

    @GetMapping("/getUnitList/{propertyId}")
    @ResponseBody
    public List<Unit> getUnitList(@PathVariable Long propertyId) {
        return propertyService.getAvailableUnitsByPropertyId(propertyId);
    }



}