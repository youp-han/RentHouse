package com.jjst.rentManagement.renthouse.service;

import java.io.ByteArrayInputStream;

public interface ReportService {
    ByteArrayInputStream generateMonthlyRevenueReport();
    ByteArrayInputStream generateTenantStatusReport();
}
