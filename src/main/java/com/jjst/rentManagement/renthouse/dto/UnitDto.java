package com.jjst.rentManagement.renthouse.dto;

import lombok.Data;

@Data
public class UnitDto {
    private long id;
    private String unitNumber;
    private boolean rentStatus;
    private Double size_meter;
    private Double size_korea;
    private String useType;
    private String description;
}