package com.jjst.rentManagement.renthouse.module.Members.entity;

import com.jjst.rentManagement.renthouse.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="member")
public class Member extends BaseEntity {
    private String name; //name of the renter
    private String rentPayer; // if different from renter's name
    private String phone;
    private String email;
    private String role; //admin, user
    private String snsId;
    private String snsType; //0: none: 1:naver, 2:kakao, 3:google
    private boolean isNew=true;
    private boolean approved=false; //if approved=true, isNew will be false;
}
