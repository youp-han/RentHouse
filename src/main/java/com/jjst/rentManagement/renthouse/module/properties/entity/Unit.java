package com.jjst.rentManagement.renthouse.module.properties.entity;

import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Unit extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    private String unitNumber = "";;
    private Boolean rentStatus = false;

    private Double size_meter;          // 면적(㎡)
    private Double size_korea;
    private String useType;       // 사무실/상가/주거
    //private BigDecimal monthlyRent; //
    private String description; // extra info

}
