package com.jjst.rentManagement.renthouse.module.Properties.entity;

import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.spi.PropertyType;

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
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Unit> units = new ArrayList<>();

}

