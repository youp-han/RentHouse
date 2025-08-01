package com.jjst.rentManagement.renthouse.dto;

import lombok.Data;

@Data
public class PropertyDto {
    private long propertyId;
    private String name;
    private String address;
    private String zipCode;
    private String roadAddress;
    private String detailAddress;
    private String registerdTo;
    private String type;
    private Integer totalFloors;
    private String totalFloorsSelect;
    private Integer totalUnits;
}

