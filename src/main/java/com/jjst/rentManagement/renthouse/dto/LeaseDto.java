package com.jjst.rentManagement.renthouse.dto;

import com.jjst.rentManagement.renthouse.module.common.entity.MembershipType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LeaseDto {
    private Long memberId;
    private Long unitId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal deposit;
    private BigDecimal monthlyRent;
    private String contractNotes;
    private MembershipType membershipType;  // "MONTHLY", "YEARLY", "JEONSE" 등의 문자열 값
}


