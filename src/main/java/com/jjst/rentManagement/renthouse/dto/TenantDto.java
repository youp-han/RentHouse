package com.jjst.rentManagement.renthouse.dto;

import lombok.Data;

@Data
public class TenantDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String socialNo;
    private String currentAddress;
}