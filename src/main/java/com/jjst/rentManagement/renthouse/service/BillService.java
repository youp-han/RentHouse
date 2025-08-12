package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.dto.BillDto;
import com.jjst.rentManagement.renthouse.module.bills.entity.Bill;

import java.util.List;

public interface BillService {
    List<BillDto> getAllBills();
    BillDto createBill(BillDto billDto);
    Bill getBillById(Long id) throws Exception;
    List<Bill> getAllBillEntities();
}
