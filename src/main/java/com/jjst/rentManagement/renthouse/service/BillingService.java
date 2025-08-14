package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.dto.BillingDto;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;

import java.util.List;
import java.util.Map;

public interface BillingService {
    Map<String, Double> getMonthlyIncomeForLastSixMonths();

    List<BillingDto> getAllBillings();

    void generateMonthlyBillingsForLease(Lease lease);
}
