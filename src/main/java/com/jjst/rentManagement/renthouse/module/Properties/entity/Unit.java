package com.jjst.rentManagement.renthouse.module.Properties.entity;

import com.jjst.rentManagement.renthouse.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Unit extends BaseEntity {

    private String unitNumber = "";;
    private String rentStatus = "N";
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnitAttribute> unitAttributes = new ArrayList<>();

}
