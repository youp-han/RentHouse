package com.jjst.rentManagement.renthouse.dto;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String name =""; //name of the renter
    private String phoneNumber ="";
    private String email ="";
    private String password ="";
    private String role =""; //admin, user
    private String snsType ="";
    private String snsId ="";
    private boolean deleted = false;
    private boolean newUser = true;
    private boolean approved = false; //if approved=true, isNew will be false;

}
