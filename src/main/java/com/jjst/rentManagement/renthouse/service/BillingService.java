package com.jjst.rentManagement.renthouse.service;

import java.util.Map;

public interface BillingService {
    Map<String, Double> getMonthlyIncomeForLastSixMonths();
}