package com.jjst.rentManagement.renthouse.service;

import com.jjst.rentManagement.renthouse.dto.MemberDto;
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
    List<Member> getNewMemberByNewUserTrue();
    //post
    void registerMember(Member member, String rawPassword) throws Exception;
    Member getById(long id);
    Member save(Member member) throws Exception;
    void updateMember(Member member) throws Exception;
    void changePassword(Member member, String newPassword) throws Exception;
    void deleteMember(Member member) throws Exception;
    void resetPassword(Long memberId, String newPassword) throws Exception;

    void processMemberApplication(MemberDto memberDto) throws Exception;
}