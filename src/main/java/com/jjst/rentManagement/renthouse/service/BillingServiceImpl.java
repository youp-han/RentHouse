package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.module.bills.repository.BillingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;

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
}
