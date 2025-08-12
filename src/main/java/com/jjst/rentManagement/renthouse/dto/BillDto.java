package com.jjst.rentManagement.renthouse.dto;

import com.jjst.rentManagement.renthouse.module.common.enums.BillCategory;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillDto {
    private Long id;
    private String name;
    private BillCategory category;
    private BigDecimal amount;
    private String description;
}
