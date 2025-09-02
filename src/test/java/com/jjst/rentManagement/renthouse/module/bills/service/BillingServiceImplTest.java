package com.jjst.rentManagement.renthouse.module.bills.service;

import com.jjst.rentManagement.renthouse.dto.BillingDto;
import com.jjst.rentManagement.renthouse.module.bills.entity.Billing;
import com.jjst.rentManagement.renthouse.module.bills.repository.BillingRepository;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillingServiceImplTest {

    @Mock
    private BillingRepository billingRepository;

    @Mock
    private EntityConverter entityConverter;

    @InjectMocks
    private BillingServiceImpl billingService;

    private Lease lease;

    @BeforeEach
    void setUp() {
        lease = new Lease();
        lease.setId(1L);
        lease.setMonthlyRent(new BigDecimal("500000"));
    }

    @Test
    void generateMonthlyBillingsForLease_whenBillDoesNotExist() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        when(billingRepository.existsByLeaseAndDueDateBetween(lease, startOfMonth, endOfMonth)).thenReturn(false);

        billingService.generateMonthlyBillingsForLease(lease);

        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    @Test
    void generateMonthlyBillingsForLease_whenBillAlreadyExists() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        when(billingRepository.existsByLeaseAndDueDateBetween(lease, startOfMonth, endOfMonth)).thenReturn(true);

        billingService.generateMonthlyBillingsForLease(lease);

        verify(billingRepository, never()).save(any(Billing.class));
    }

    @Test
    void getAllBillings() {
        Billing billing = new Billing();
        billing.setId(1L);
        BillingDto billingDto = new BillingDto();
        billingDto.setId(1L);

        when(billingRepository.findAll()).thenReturn(Collections.singletonList(billing));
        when(entityConverter.convertToDto(any(Billing.class), eq(BillingDto.class))).thenReturn(billingDto);

        List<BillingDto> billings = billingService.getAllBillings();

        assertNotNull(billings);
        assertEquals(1, billings.size());
        verify(billingRepository, times(1)).findAll();
        verify(entityConverter, times(1)).convertToDto(any(Billing.class), eq(BillingDto.class));
    }
}
