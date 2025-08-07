package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.dto.LeaseDto;
import com.jjst.rentManagement.renthouse.module.common.enums.LeaseType;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import com.jjst.rentManagement.renthouse.service.LeaseService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.service.TenantService;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/leases")
public class LeaseController {

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private EntityConverter entityConverter;

    @GetMapping
    public String getAllLeases(Model model) {
        List<Lease> leases = leaseService.getAllLeases();
        model.addAttribute("leases", leases);
        return "lease/leaseList";
    }

    @GetMapping("/register")
    public String registerLeaseForm(Model model) {
        List<Tenant> tenants = tenantService.getAllTenants();
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("tenants", tenants);
        model.addAttribute("properties", properties);
        model.addAttribute("leaseTypes", LeaseType.values());
        return "lease/register";
    }

    

    @PostMapping
    @ResponseBody
    public ResponseEntity<LeaseDto> createLease(@RequestBody LeaseDto leaseDto) {
        try {
            Lease lease = leaseService.registerLease(leaseDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(entityConverter.convertToDto(lease, LeaseDto.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/edit/{id}")
    public String editLeaseForm(@PathVariable Long id, Model model) {
        try {
            Lease lease = leaseService.getLeaseById(id);
            model.addAttribute("lease", entityConverter.convertToDto(lease, LeaseDto.class));
            model.addAttribute("tenants", tenantService.getAllTenants());
            model.addAttribute("properties", propertyService.getAllProperties());
            model.addAttribute("leaseTypes", LeaseType.values());
            model.addAttribute("leaseStatusTypes", com.jjst.rentManagement.renthouse.module.common.enums.LeaseStatus.values());
            return "lease/edit";
        } catch (Exception e) {
            // Handle error, e.g., redirect to lease list with an error message
            return "redirect:/leases";
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LeaseDto> getLeaseById(@PathVariable Long id) {
        try {
            Lease lease = leaseService.getLeaseById(id);
            return ResponseEntity.ok(entityConverter.convertToDto(lease, LeaseDto.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<LeaseDto> updateLease(@PathVariable Long id, @RequestBody LeaseDto leaseDto) {
        try {
            Lease updatedLease = leaseService.updateLease(id, leaseDto);
            return ResponseEntity.ok(entityConverter.convertToDto(updatedLease, LeaseDto.class));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteLease(@PathVariable Long id) {
        try {
            leaseService.deleteLease(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

