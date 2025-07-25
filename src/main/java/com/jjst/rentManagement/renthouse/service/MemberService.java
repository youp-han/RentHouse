package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.module.members.entity.Member;

import java.util.List;

public interface MemberService {

    String ROLE_ADMIN="admin";
    //get
    List<Member> getAll();
    boolean authenticate(String rawPassword, String storedPassword);
    boolean checkEmail(String email);
    Member getMemberByEmail(String email);
    Member getMemberBySnsId(String snsId);
    List<Member> getNewMemberByIsNewTrue();
    //post
    void registerMember(Member member, String rawPassword) throws Exception;
    Member getById(long id);
    void save(Member member) throws Exception;
}
