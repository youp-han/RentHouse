package com.jjst.rentManagement.renthouse.dto;

import lombok.Data;

@Data
public class PropertyDto {
    private long propertyId;
    private String name;
    private String address;
    private String registerdTo;
}

