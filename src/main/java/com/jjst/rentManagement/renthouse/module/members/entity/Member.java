package com.jjst.rentManagement.renthouse.module.members.entity;

import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import com.jjst.rentManagement.renthouse.module.common.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="member", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member extends BaseEntity {
    private String name; //name of the renter
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;
    private String password; //hashed password

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    private String snsId;
    private String snsType; //0: none: 1:naver, 2:kakao, 3:google
    private boolean newUser;
    private boolean approved; //if approved=true, isNew will be false;
    private boolean deleted=false;
}
