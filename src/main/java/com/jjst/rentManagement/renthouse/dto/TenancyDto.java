package com.jjst.rentManagement.renthouse.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TenancyDto {
    private Long memberId;
    private Long unitId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double deposit;
    private double monthlyRent;
    private String contractNotes;
    private String membershipType;  // "MONTHLY", "YEARLY", "JEONSE" 등의 문자열 값
}


