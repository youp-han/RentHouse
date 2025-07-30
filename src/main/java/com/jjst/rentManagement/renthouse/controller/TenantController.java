package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.dto.TenantDto;
import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import com.jjst.rentManagement.renthouse.service.TenantService;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private EntityConverter entityConverter;

    @GetMapping
    public String getAllTenants(Model model) {
        List<Tenant> tenants = tenantService.getAllTenants();
        List<TenantDto> tenantDtos = tenants.stream()
                .map(tenant -> entityConverter.convertToDto(tenant, TenantDto.class))
                .collect(Collectors.toList());
        model.addAttribute("tenants", tenantDtos);
        return "admin/tenantsList";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TenantDto> getTenantById(@PathVariable Long id) {
        Tenant tenant = tenantService.getTenantById(id);
        if (tenant != null) {
            return ResponseEntity.ok(entityConverter.convertToDto(tenant, TenantDto.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<TenantDto> createTenant(@RequestBody TenantDto tenantDto) {
        Tenant tenant = entityConverter.convertToEntity(tenantDto, Tenant.class);
        Tenant savedTenant = tenantService.saveTenant(tenant);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityConverter.convertToDto(savedTenant, TenantDto.class));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TenantDto> updateTenant(@PathVariable Long id, @RequestBody TenantDto tenantDto) {
        Tenant existingTenant = tenantService.getTenantById(id);
        if (existingTenant != null) {
            existingTenant.setName(tenantDto.getName());
            existingTenant.setPhone(tenantDto.getPhone());
            existingTenant.setEmail(tenantDto.getEmail());
            existingTenant.setSocialNo(tenantDto.getSocialNo());
            existingTenant.setCurrentAddress(tenantDto.getCurrentAddress());

            Tenant updatedTenant = tenantService.saveTenant(existingTenant);
            return ResponseEntity.ok(entityConverter.convertToDto(updatedTenant, TenantDto.class));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.noContent().build();
    }
}
