package com.jjst.rentManagement.renthouse.module.Members.entity;

import com.jjst.rentManagement.renthouse.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name="member", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Member extends BaseEntity {
    private String name; //name of the renter
    private String rentPayer; // if different from renter's name
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;
    private String password; //hashed password

    private String role; //admin, user
    private String snsId;
    private String snsType; //0: none: 1:naver, 2:kakao, 3:google
    private boolean isNew;
    private boolean approved; //if approved=true, isNew will be false;
    private boolean deleted=false;
}
