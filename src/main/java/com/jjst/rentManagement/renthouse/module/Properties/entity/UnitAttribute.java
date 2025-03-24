package com.jjst.rentManagement.renthouse.module.Properties.entity;

import com.jjst.rentManagement.renthouse.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UnitAttribute extends BaseEntity {

    private String featureKey; // e.g., balcony, ensuite
    private String featureValue;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

}
