package com.jjst.rentManagement.renthouse.module.tenants.entity;

import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Tenant extends BaseEntity {

    private String name;
    private String phone;
    private String email;

    @Column(length = 14)
    private String socialNo;

    private String CurrentAddress;


}
