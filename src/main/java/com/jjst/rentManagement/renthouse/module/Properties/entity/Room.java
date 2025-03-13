package com.jjst.rentManagement.renthouse.module.Properties.entity;

import com.jjst.rentManagement.renthouse.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Room extends BaseEntity {

    private double size; // in square meters
    private String features; // e.g., balcony, ensuite
    public enum Type {
        oneRoom,
        twoRooms,
        threeRooms
    }; // e.g., bedroom, kitchen, bathroom

    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

}
