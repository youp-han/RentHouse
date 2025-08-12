package com.jjst.rentManagement.renthouse.module.bills.service;

import com.jjst.rentManagement.renthouse.dto.BillingDto;
import com.jjst.rentManagement.renthouse.module.bills.entity.Bill;
import com.jjst.rentManagement.renthouse.module.bills.entity.Billing;
import com.jjst.rentManagement.renthouse.module.bills.entity.BillingItem;
import com.jjst.rentManagement.renthouse.module.bills.repository.BillingRepository;
import com.jjst.rentManagement.renthouse.module.common.enums.BillCategory;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.service.BillService;
import com.jjst.rentManagement.renthouse.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private BillService billService;

    @Override
    public List<BillingDto> getAllBillings() {
        return billingRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void generateMonthlyBillingsForLease(Lease lease) {
        List<Bill> allBills = billService.getAllBillEntities();
        LocalDate startDate = lease.getStartDate();

        for (int i = 0; i < 12; i++) {
            LocalDate billingDate = startDate.plusMonths(i);
            YearMonth billingPeriod = YearMonth.from(billingDate);

            Billing billing = new Billing();
            billing.setLease(lease);
            billing.setPeriod(billingPeriod);
            billing.setIssueDate(billingDate.withDayOfMonth(1));
            billing.setDueDate(billingDate.plusMonths(1).withDayOfMonth(1));
            billing.setPaid(false);

            List<BillingItem> items = new ArrayList<>();
            for (Bill bill : allBills) {
                if (bill.getCategory() == BillCategory.PARKING) {
                    if (lease.getContractNotes() != null && lease.getContractNotes().contains("주차")) {
                        BillingItem item = new BillingItem();
                        item.setBilling(billing);
                        item.setBill(bill);
                        item.setAmount(bill.getAmount());
                        items.add(item);
                    }
                } else {
                    BillingItem item = new BillingItem();
                    item.setBilling(billing);
                    item.setBill(bill);
                    item.setAmount(bill.getAmount());
                    items.add(item);
                }
            }

            billing.setItems(items);
            billing.updateTotalAmount();
            billingRepository.save(billing);
        }
    }

    private BillingDto convertToDto(Billing billing) {
        BillingDto dto = new BillingDto();
        dto.setId(billing.getId());
        dto.setLeaseId(billing.getLease().getId());
        dto.setPeriod(billing.getPeriod());
        dto.setAmount(billing.getTotalAmount()); // Use totalAmount
        if (billing.getIssueDate() != null) {
            dto.setIssueDate(billing.getIssueDate().toString());
        }
        if (billing.getDueDate() != null) {
            dto.setDueDate(billing.getDueDate().toString());
        }
        dto.setPaid(billing.getPaid());
        if (billing.getPaidDate() != null) {
            dto.setPaidDate(billing.getPaidDate().toString());
        }

        // Populate lease info
        Lease lease = billing.getLease();
        dto.setTenantName(lease.getTenant().getName());
        dto.setPropertyName(lease.getUnit().getProperty().getName());
        dto.setUnitNumber(lease.getUnit().getUnitNumber());

        // For simplicity, I'm not including all item details in the main list DTO.
        // This can be fetched on demand.
        return dto;
    }
}
