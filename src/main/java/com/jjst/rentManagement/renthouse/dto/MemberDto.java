package com.jjst.rentManagement.renthouse.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MemberDto {

    private String name =""; //name of the renter
    private String phone ="";
    private String email ="";
    private String password ="";
    private String role =""; //admin, user
    private String snsType ="";
    private String snsId ="";
    private boolean deleted = false;
    private boolean isNew = true;
    private boolean approved = false; //if approved=true, isNew will be false;

}
