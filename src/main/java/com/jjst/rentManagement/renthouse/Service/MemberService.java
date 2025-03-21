package com.jjst.rentManagement.renthouse.Service;

import com.jjst.rentManagement.renthouse.module.Members.entity.Member;

import java.util.List;

public interface MemberService {
    //get
    List<Member> getAll();
    boolean authenticate(String rawPassword, String storedPassword);
    boolean checkEmail(String email);
    Member getMemberByEmail(String email);

    //post
    void registerMember(Member member, String rawPassword);

}
