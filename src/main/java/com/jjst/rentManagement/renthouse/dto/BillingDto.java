package com.jjst.rentManagement.renthouse.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;

@Getter
@Setter
public class BillingDto {

    private Long id;
    private Long leaseId;
    private Long billId;
    private String billName;
    private YearMonth period;
    private BigDecimal amount;
    private String issueDate;
    private String dueDate;
    private Boolean paid;
    private String paidDate;

    // For displaying lease info in the list
    private String tenantName;
    private String propertyName;
    private String unitNumber;
}