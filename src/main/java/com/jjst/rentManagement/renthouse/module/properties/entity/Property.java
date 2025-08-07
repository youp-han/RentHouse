package com.jjst.rentManagement.renthouse.module.properties.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import com.jjst.rentManagement.renthouse.module.common.enums.PropertyType;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Property extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private PropertyType type;    // APARTMENT, BUILDING, etc.
    private String address;
    private String name;
    private Integer totalFloors;
    private Integer totalUnits; // 총 세대수
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Unit> units = new ArrayList<>();

}

