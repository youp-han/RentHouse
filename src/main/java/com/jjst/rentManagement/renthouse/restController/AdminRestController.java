package com.jjst.rentManagement.renthouse.restController;

import com.jjst.rentManagement.renthouse.dto.LeaseDto;
import com.jjst.rentManagement.renthouse.dto.UnitDto;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;

import com.jjst.rentManagement.renthouse.service.LeaseService;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
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
    private LeaseService leaseService;

    @GetMapping("/admin/property/units/{propertyId}")
    public List<UnitDto> getUnitsByPropertyId(@PathVariable long propertyId) {
        List<Unit> unitList = propertyService.getUnitsByPropertyId(propertyId);
        List<UnitDto>dtoList = new ArrayList<>();
        for(Unit unit : unitList){
            if(unit.getRentStatus() != false){
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
    public String saveProperty(@RequestParam String address) throws Exception {

        Property property = new Property();
        property.setAddress(address);

        propertyService.saveProperty(property);

        return "redirect:/admin/home";
    }

    @PostMapping("/admin/property/unit/save")
    public String saveUnit(@RequestParam Long propertyId, @RequestParam String unitNumber) throws Exception {
        Property property = propertyService.getPropertyById(propertyId);
        Unit unit = new Unit();
        unit.setUnitNumber(unitNumber);
        //unit.setProperty_id(property_id);
        unit.setRentStatus(false);
        //property.getUnits().add(unit);
        propertyService.saveProperty(property);
        return "redirect:/admin/home";
    }


    @PostMapping("/admin/tenancy/save")
    @ResponseBody
    public ResponseEntity<Map<String, String>> savelease(@RequestBody LeaseDto leaseDto, Principal principal) {
        Lease lease = new Lease();
        Member member = memberService.getById(leaseDto.getMemberId());
        Unit unit = propertyService.getUnitById(leaseDto.getUnitId());

        if(member!=null && unit!=null){
            lease.setMember(member);
            lease.setUnit(unit);
        } else{
            throw new RuntimeException("Memger or Unit not available.");
        }

        lease.setStartDate(leaseDto.getStartDate());
        lease.setEndDate(leaseDto.getEndDate());
        lease.setDeposit(leaseDto.getDeposit());
        lease.setMonthlyRent(leaseDto.getMonthlyRent());
        lease.setContractNotes(leaseDto.getContractNotes());
        if (leaseDto.getMembershipType() != null) {
            try {
                lease.setMembershipType(leaseDto.getMembershipType());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid MembershipType value: " + leaseDto.getMembershipType());
            }
        }
        lease.setCreatedBy(principal.getName());
        lease.setLastModifiedBy(principal.getName());

        member.setNew(false);
        member.setApproved(true);
        unit.setRentStatus(true);

        Map<String, String> response = new HashMap<>();
        try {
            leaseService.registerLease(lease, member, unit);
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "failure" + e.getMessage());
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
