package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.dto.BillingDto;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;

import java.util.List;

public interface BillingService {
    List<BillingDto> getAllBillings();
    void generateMonthlyBillingsForLease(Lease lease);
}
