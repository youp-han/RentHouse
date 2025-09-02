package com.jjst.rentManagement.renthouse.module.bills.service;

import com.jjst.rentManagement.renthouse.dto.BillingDto;
import com.jjst.rentManagement.renthouse.module.bills.entity.Billing;
import com.jjst.rentManagement.renthouse.module.bills.repository.BillingRepository;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.service.BillingService; // 상위 패키지의 인터페이스를 import
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private EntityConverter entityConverter;

    @Override
    public Map<String, Double> getMonthlyIncomeForLastSixMonths() {
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(5).withDayOfMonth(1);
        List<Map<String, Object>> monthlyIncomeData = billingRepository.findMonthlyIncomeSince(sixMonthsAgo);

        Map<String, Double> incomeByMonth = monthlyIncomeData.stream()
                .collect(Collectors.toMap(
                        row -> (String) row.get("month"),
                        row -> ((BigDecimal) row.get("total")).doubleValue()
                ));

        Map<String, Double> result = new LinkedHashMap<>();
        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        for (int i = 5; i >= 0; i--) {
            YearMonth month = currentMonth.minusMonths(i);
            String monthStr = month.format(formatter);
            result.put(monthStr, incomeByMonth.getOrDefault(monthStr, 0.0));
        }

        return result;
    }

    @Override
    public List<BillingDto> getAllBillings() {
        // TODO: Implement this method
        List<Billing> billings = billingRepository.findAll();
        List<BillingDto> billingDtos = new ArrayList<>();
        for (Billing billing : billings) {
            billingDtos.add(entityConverter.convertToDto(billing, BillingDto.class));
        }

        return billingDtos;
    }

    @Override
    public void generateMonthlyBillingsForLease(Lease lease) {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        boolean billExists = billingRepository.existsByLeaseAndDueDateBetween(lease, startOfMonth, endOfMonth);

        if (!billExists) {
            Billing billing = new Billing();
            billing.setLease(lease);
            billing.setTotalAmount(lease.getMonthlyRent());
            billing.setDueDate(endOfMonth);
            billing.setIssueDate(today);
            billing.setPaid(false);
            billingRepository.save(billing);
        }
    }
}
