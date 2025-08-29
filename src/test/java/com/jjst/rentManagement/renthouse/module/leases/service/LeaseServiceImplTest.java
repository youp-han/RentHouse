
package com.jjst.rentManagement.renthouse.module.leases.service;

import com.jjst.rentManagement.renthouse.dto.LeaseDto;
import com.jjst.rentManagement.renthouse.module.activity.entity.ActivityType;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.module.leases.repository.LeaseRepository;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import com.jjst.rentManagement.renthouse.service.ActivityLogService;
import com.jjst.rentManagement.renthouse.service.BillingService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.service.TenantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeaseServiceImplTest {

    @Mock
    private LeaseRepository leaseRepository;

    @Mock
    private PropertyService propertyService;

    @Mock
    private TenantService tenantService;

    @Mock
    private BillingService billingService;

    @Mock
    private ActivityLogService activityLogService;

    @InjectMocks
    private LeaseServiceImpl leaseService;

    private LeaseDto leaseDto;
    private Tenant tenant;
    private Unit unit;
    private Property property;

    @BeforeEach
    void setUp() {
        leaseDto = new LeaseDto();
        leaseDto.setTenantId(1L);
        leaseDto.setUnitId(1L);
        leaseDto.setStartDate("2025-01-01");
        leaseDto.setEndDate("2027-01-01");
        leaseDto.setDeposit(new BigDecimal("10000000"));
        leaseDto.setMonthlyRent(new BigDecimal("500000"));

        tenant = new Tenant();
        tenant.setId(1L);
        tenant.setName("테스트 임차인");

        property = new Property();
        property.setId(1L);
        property.setName("테스트 빌딩");
        property.setAddress("서울시 테스트구 테스트동");

        unit = new Unit();
        unit.setId(1L);
        unit.setUnitNumber("101호");
        unit.setRentStatus(false); // 임대되지 않은 상태
        unit.setProperty(property);
    }

    @Test
    @DisplayName("신규 임대차 계약 등록 성공")
    void registerLease_Success() throws Exception {
        // given
        when(tenantService.getTenantById(1L)).thenReturn(tenant);
        when(propertyService.getUnitById(1L)).thenReturn(unit);
        when(leaseRepository.save(any(Lease.class))).thenAnswer(invocation -> {
            Lease lease = invocation.getArgument(0);
            lease.setId(1L); // Simulate saving and getting an ID
            return lease;
        });

        // when
        Lease savedLease = leaseService.registerLease(leaseDto);

        // then
        assertNotNull(savedLease);
        assertEquals(1L, savedLease.getId());
        assertEquals(tenant, savedLease.getTenant());
        assertEquals(unit, savedLease.getUnit());
        assertTrue(unit.getRentStatus()); // 유닛의 임대 상태가 true로 변경되었는지 확인
        assertEquals("서울시 테스트구 테스트동, 101호", tenant.getCurrentAddress()); // 임차인 주소가 업데이트되었는지 확인

        // verify
        verify(tenantService, times(1)).getTenantById(1L);
        verify(propertyService, times(1)).getUnitById(1L);
        verify(tenantService, times(1)).saveTenant(tenant);
        verify(propertyService, times(1)).saveUnit(unit);
        verify(leaseRepository, times(1)).save(any(Lease.class));
        verify(activityLogService, times(1)).logActivity(eq(ActivityType.NEW_LEASE), anyString(), eq(1L));
        verify(billingService, times(1)).generateMonthlyBillingsForLease(any(Lease.class));
    }

    @Test
    @DisplayName("이미 임대된 유닛에 대한 계약 등록 시 예외 발생")
    void registerLease_Fail_UnitAlreadyRented() throws Exception {
        // given
        unit.setRentStatus(true); // 유닛이 이미 임대된 상태로 설정
        when(tenantService.getTenantById(1L)).thenReturn(tenant);
        when(propertyService.getUnitById(1L)).thenReturn(unit);

        // when & then
        Exception exception = assertThrows(Exception.class, () -> {
            leaseService.registerLease(leaseDto);
        });

        assertEquals("Unit is already rented", exception.getMessage());

        // verify
        verify(leaseRepository, never()).save(any(Lease.class)); // save 메소드가 호출되지 않았는지 확인
        verify(billingService, never()).generateMonthlyBillingsForLease(any(Lease.class));
    }

    @Test
    @DisplayName("존재하지 않는 임차인으로 계약 등록 시 예외 발생")
    void registerLease_Fail_TenantNotFound() throws Exception {
        // given
        when(tenantService.getTenantById(1L)).thenReturn(null); // 임차인이 없는 경우

        // when & then
        Exception exception = assertThrows(Exception.class, () -> {
            leaseService.registerLease(leaseDto);
        });

        assertEquals("Tenant or Unit not found", exception.getMessage());

        // verify
        verify(leaseRepository, never()).save(any(Lease.class));
    }
}
