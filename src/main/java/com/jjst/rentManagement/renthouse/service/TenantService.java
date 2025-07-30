package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import java.util.List;

public interface TenantService {
    List<Tenant> getAllTenants();
    Tenant getTenantById(Long id);
    Tenant saveTenant(Tenant tenant);
    void deleteTenant(Long id);
}