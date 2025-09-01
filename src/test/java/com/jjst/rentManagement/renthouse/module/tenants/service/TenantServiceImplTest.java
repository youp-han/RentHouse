package com.jjst.rentManagement.renthouse.module.tenants.service;

import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import com.jjst.rentManagement.renthouse.module.tenants.repository.TenantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TenantServiceImplTest {

    @Mock
    private TenantRepository tenantRepository;

    @InjectMocks
    private TenantServiceImpl tenantService;

    private Tenant tenant;

    @BeforeEach
    void setUp() {
        tenant = new Tenant();
        tenant.setId(1L);
        tenant.setName("Test Tenant");
        tenant.setEmail("test@example.com");
    }

    @Test
    void getAllTenants() {
        when(tenantRepository.findAll()).thenReturn(Collections.singletonList(tenant));

        List<Tenant> tenants = tenantService.getAllTenants();

        assertNotNull(tenants);
        assertEquals(1, tenants.size());
        assertEquals("Test Tenant", tenants.get(0).getName());
        verify(tenantRepository, times(1)).findAll();
    }

    @Test
    void getTenantById() {
        when(tenantRepository.findById(1L)).thenReturn(Optional.of(tenant));

        Tenant foundTenant = tenantService.getTenantById(1L);

        assertNotNull(foundTenant);
        assertEquals("Test Tenant", foundTenant.getName());
        verify(tenantRepository, times(1)).findById(1L);
    }

    @Test
    void getTenantById_NotFound() {
        when(tenantRepository.findById(1L)).thenReturn(Optional.empty());

        Tenant foundTenant = tenantService.getTenantById(1L);

        assertNull(foundTenant);
        verify(tenantRepository, times(1)).findById(1L);
    }

    @Test
    void saveTenant() {
        when(tenantRepository.save(any(Tenant.class))).thenReturn(tenant);

        Tenant savedTenant = tenantService.saveTenant(new Tenant());

        assertNotNull(savedTenant);
        assertEquals("Test Tenant", savedTenant.getName());
        verify(tenantRepository, times(1)).save(any(Tenant.class));
    }

    @Test
    void deleteTenant() {
        doNothing().when(tenantRepository).deleteById(1L);

        tenantService.deleteTenant(1L);

        verify(tenantRepository, times(1)).deleteById(1L);
    }
}
