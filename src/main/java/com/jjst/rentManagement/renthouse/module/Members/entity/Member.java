package com.jjst.rentManagement.renthouse.module.Members.entity;

import com.jjst.rentManagement.renthouse.module.common.entity.BaseEntity;
import com.jjst.rentManagement.renthouse.module.common.entity.RoleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="member", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member extends BaseEntity {
    private String name; //name of the renter

    //todo: rentPayer 를 삭제
    private String rentPayer; // if different from renter's name
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;
    private String password; //hashed password

    //todo: role 은 enum 으로 관리.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    private String snsId;
    private String snsType; //0: none: 1:naver, 2:kakao, 3:google
    private boolean isNew;
    private boolean approved; //if approved=true, isNew will be false;
    private boolean deleted=false;
}
