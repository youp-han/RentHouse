package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.dto.TenancyDto;
import com.jjst.rentManagement.renthouse.dto.UnitDto;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Tenancy.entity.Tenancy;
import com.jjst.rentManagement.renthouse.module.Tenancy.repository.TenancyRepository;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.Properties.entity.UnitAttribute;
import com.jjst.rentManagement.renthouse.module.Properties.entity.Unit;
import com.jjst.rentManagement.renthouse.service.TenancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminRestController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TenancyService tenancyService;

    @GetMapping("/admin/property/units/{propertyId}")
    public List<UnitDto> getUnitsByPropertyId(@PathVariable long propertyId) {
        List<Unit> unitList = propertyService.getUnitsByPropertyId(propertyId);
        List<UnitDto>dtoList = new ArrayList<>();
        for(Unit unit : unitList){
            if(unit.getRentStatus() != "N"){
                UnitDto unitDto = new UnitDto();
                unitDto.setId(unit.getId());
                unitDto.setUnitNumber(unit.getUnitNumber());
                unitDto.setRentStatus(unit.getRentStatus());
                dtoList.add(unitDto);
            }
        }
        return dtoList;
    }

    @PostMapping("/admin/property/save")
    public String saveProperty(@RequestParam String address) {

        Property property = new Property();
        property.setAddress(address);

        propertyService.saveProperty(property);

        return "redirect:/admin/home";
    }

    @PostMapping("/admin/property/unit/save")
    public String saveUnit(@RequestParam Long propertyId, @RequestParam String unitNumber) {
        Property property = propertyService.getPropertyById(propertyId);
        Unit unit = new Unit();
        unit.setUnitNumber(unitNumber);
        unit.setProperty(property);
        unit.setRentStatus("n");
        property.getUnits().add(unit);
        propertyService.saveProperty(property);
        return "redirect:/admin/home";
    }


    @PostMapping("/admin/tenancy/save")
    @ResponseBody
    public ResponseEntity<Map<String, String>> saveTenancy(@RequestBody TenancyDto tenancyDto, Principal principal) {
        Tenancy tenancy = new Tenancy();
        Member member = memberService.getById(tenancyDto.getMemberId());
        Unit unit = propertyService.getUnitById(tenancyDto.getUnitId());
        if(member!=null){
            tenancy.setMember(member);
        }
        if(unit!=null){
            tenancy.setUnit(unit);
        }
        tenancy.setStartDate(tenancyDto.getStartDate());
        tenancy.setEndDate(tenancyDto.getEndDate());
        tenancy.setDeposit(tenancyDto.getDeposit());
        tenancy.setMonthlyRent(tenancyDto.getMonthlyRent());
        tenancy.setContractNotes(tenancyDto.getContractNotes());
        if (tenancyDto.getMembershipType() != null) {
            try {
                tenancy.setMembershipType(Tenancy.MembershipType.valueOf(tenancyDto.getMembershipType().toLowerCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid MembershipType value: " + tenancyDto.getMembershipType());
            }
        }
        tenancy.setCreatedBy(principal.getName());
        tenancy.setLastModifiedBy(principal.getName());


        Map<String, String> response = new HashMap<>();
        try {
            tenancyService.registerTenancy(tenancy);
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "failure");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/admin/property/unit/room/save")
    public String saveRoom(
            @RequestParam Long unitId,
            @RequestParam String type,
            @RequestParam double size,
            @RequestParam String features,
            RedirectAttributes redirectAttributes) {

        try {
            // Validate and fetch the unit
            Unit unit = propertyService.getUnitById(unitId);
            if (unit == null || unitId <= 0) {
                throw new IllegalArgumentException("Invalid Unit ID: " + unitId);
            }

            // Validate inputs
            if (features == null || features.trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Features cannot be empty.");
                return "redirect:/admin/property/unit/room/register?unitId=" + unitId;
            }
            if (size <= 0) {
                redirectAttributes.addFlashAttribute("error", "Room size must be a positive number.");
                return "redirect:/admin/property/unit/room/register?unitId=" + unitId;
            }

            // Create and populate the new Room object
            UnitAttribute unitAttribute = new UnitAttribute();
            //UnitAttribute setFeatureKey(key)
            //unitAttribute.setFeatureValue(value);
            unitAttribute.setUnit(unit);

            // Add room to unit and save
            unit.getUnitAttributes().add(unitAttribute);
            propertyService.saveUnit(unit);

            // Success message
            redirectAttributes.addFlashAttribute("message",
                    "Room successfully registered: Type " + type + ", Size " + size + " sqm.");
        } catch (Exception e) {
            // Handle unexpected errors
            redirectAttributes.addFlashAttribute("error", "An error occurred: " + e.getMessage());
            return "redirect:/admin/property/unit/room/register?unitId=" + unitId;
        }

        return "redirect:/admin/home";
    }


}
