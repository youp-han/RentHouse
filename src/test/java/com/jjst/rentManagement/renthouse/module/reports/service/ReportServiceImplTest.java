package com.jjst.rentManagement.renthouse.module.reports.service;

import com.jjst.rentManagement.renthouse.dto.BillingDto;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import com.jjst.rentManagement.renthouse.service.BillingService;
import com.jjst.rentManagement.renthouse.service.LeaseService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.service.TenantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {

    @Mock
    private BillingService billingService;

    @Mock
    private TenantService tenantService;

    @Mock
    private LeaseService leaseService;

    @Mock
    private PropertyService propertyService;

    @InjectMocks
    private ReportServiceImpl reportService;

    private BillingDto billingDto;
    private Tenant tenant;
    private Lease lease;
    private Property property;
    private Unit unit;

    @BeforeEach
    void setUp() {
        billingDto = new BillingDto();
        billingDto.setAmount(BigDecimal.valueOf(1000.0));
        billingDto.setPaidDate(LocalDate.now().toString());

        tenant = new Tenant();
        tenant.setId(1L);
        tenant.setName("Test Tenant");
        tenant.setEmail("tenant@example.com");
        tenant.setPhone("010-1234-5678");

        property = new Property();
        property.setId(1L);
        property.setName("Test Property");

        unit = new Unit();
        unit.setId(1L);
        unit.setUnitNumber("101");
        unit.setProperty(property);

        lease = new Lease();
        lease.setId(1L);
        lease.setTenant(tenant);
        lease.setUnit(unit);
        lease.setStartDate(LocalDate.now().minusMonths(1));
        lease.setEndDate(LocalDate.now().plusMonths(11));
        lease.setMonthlyRent(BigDecimal.valueOf(500000));
    }

    @Test
    void generateMonthlyRevenueReport() {
        when(billingService.getAllBillings()).thenReturn(Collections.singletonList(billingDto));

        ByteArrayInputStream report = reportService.generateMonthlyRevenueReport();

        assertNotNull(report);
        assertTrue(report.available() > 0);
        verify(billingService, times(1)).getAllBillings();
    }

    @Test
    void generateTenantStatusReport() {
        when(tenantService.getAllTenants()).thenReturn(Collections.singletonList(tenant));
        when(leaseService.getAllLeases()).thenReturn(Collections.singletonList(lease));
        when(propertyService.getAllProperties()).thenReturn(Collections.singletonList(property));
        when(propertyService.getAllUnits()).thenReturn(Collections.singletonList(unit));

        ByteArrayInputStream report = reportService.generateTenantStatusReport();

        assertNotNull(report);
        assertTrue(report.available() > 0);
        verify(tenantService, times(1)).getAllTenants();
        verify(leaseService, times(1)).getAllLeases();
        verify(propertyService, times(1)).getAllProperties();
        verify(propertyService, times(1)).getAllUnits();
    }
}
