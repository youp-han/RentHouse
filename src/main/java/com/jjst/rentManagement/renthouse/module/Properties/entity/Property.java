package com.jjst.rentManagement.renthouse.module.Properties.entity;

import com.jjst.rentManagement.renthouse.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Property extends BaseEntity {
    private String address;
    private String nickName;
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Unit> units = new ArrayList<>();

}
