package com.jjst.rentManagement.renthouse.module.bills.service;

import com.jjst.rentManagement.renthouse.dto.BillDto;
import com.jjst.rentManagement.renthouse.module.bills.entity.Bill;
import com.jjst.rentManagement.renthouse.module.bills.repository.BillRepository;
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
class BillServiceImplTest {

    @Mock
    private BillRepository billRepository;

    @InjectMocks
    private BillServiceImpl billService;

    private Bill bill;
    private BillDto billDto;

    @BeforeEach
    void setUp() {
        bill = new Bill();
        bill.setId(1L);
        bill.setName("Test Bill");
        bill.setAmount(java.math.BigDecimal.valueOf(100.0));

        billDto = new BillDto();
        billDto.setId(1L);
        billDto.setName("Test Bill DTO");
        billDto.setAmount(java.math.BigDecimal.valueOf(100.0));
    }

    @Test
    void createBill() {
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        BillDto createdBillDto = billService.createBill(billDto);

        assertNotNull(createdBillDto);
        assertEquals("Test Bill", createdBillDto.getName());
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    void getAllBills() {
        when(billRepository.findAll()).thenReturn(Collections.singletonList(bill));

        List<BillDto> bills = billService.getAllBills();

        assertNotNull(bills);
        assertEquals(1, bills.size());
        assertEquals("Test Bill", bills.get(0).getName());
        verify(billRepository, times(1)).findAll();
    }

    @Test
    void getBillById() throws Exception {
        when(billRepository.findById(1L)).thenReturn(Optional.of(bill));

        Bill foundBill = billService.getBillById(1L);

        assertNotNull(foundBill);
        assertEquals("Test Bill", foundBill.getName());
        verify(billRepository, times(1)).findById(1L);
    }

    @Test
    void getBillById_NotFound() {
        when(billRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            billService.getBillById(1L);
        });

        verify(billRepository, times(1)).findById(1L);
    }

    @Test
    void updateBill() {
        Bill updatedBill = new Bill();
        updatedBill.setId(1L);
        updatedBill.setName("Updated Bill");
        updatedBill.setAmount(java.math.BigDecimal.valueOf(200.0));

        when(billRepository.findById(1L)).thenReturn(Optional.of(bill));
        when(billRepository.save(any(Bill.class))).thenReturn(updatedBill);

        BillDto result = billService.updateBill(billDto);

        assertNotNull(result);
        assertEquals("Updated Bill", result.getName());
        assertEquals(java.math.BigDecimal.valueOf(200.0), result.getAmount());
        verify(billRepository, times(1)).findById(1L);
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    void deleteBill() {
        doNothing().when(billRepository).deleteById(1L);

        billService.deleteBill(1L);

        verify(billRepository, times(1)).deleteById(1L);
    }
}
