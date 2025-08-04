package com.jjst.rentManagement.renthouse.module.members.service;

import com.jjst.rentManagement.renthouse.dto.MemberDto;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.members.repository.MemberRepository;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityConverter entityConverter;

    //Get
    @Override
    public List<Member> getAll(){ return memberRepository.findAll(); }

    @Override
    public boolean authenticate(String rawPassword, String storedPassword){
        boolean result = passwordEncoder.matches(rawPassword, storedPassword);
        return result;
    }

    @Override
    public Member getMemberBySnsId(String snsId) {return memberRepository.findBySnsId(snsId);}

    @Override
    public Member getMemberByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    @Override
    public boolean checkEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    @Override
    public List<Member> getNewMemberByNewUserTrue(){
        return memberRepository.findByNewUserTrue();
    }

    @Override
    public Member getById(long id){
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found for id: " + id));
    }


    //Post
    @Override
    public void registerMember(Member member, String rawPassword) throws Exception {
        String email = member.getEmail().toLowerCase().trim();
        if(memberRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email Already Exists");
        }
        String encryptgedPassword = passwordEncoder.encode(rawPassword);
        member.setPassword(encryptgedPassword);
        this.save(member);
    }

    @Override
    public Member save(Member member) throws Exception {
        try{
            return memberRepository.save(member);
        }catch(Exception e){
            throw new RuntimeException("Error saving member", e);
        }

    }

    @Override
    public void updateMember(Member member) throws Exception {
        Member existingMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new RuntimeException("Member not found for id: " + member.getId()));

        // Update only allowed fields
        existingMember.setName(member.getName());
        existingMember.setPhoneNumber(member.getPhoneNumber());
        // Email and Role should not be updated via this method.

        try {
            memberRepository.save(existingMember);
        } catch (Exception e) {
            throw new RuntimeException("Error updating member", e);
        }
    }

    @Override
    public void changePassword(Member member, String newPassword) throws Exception {
        Member existingMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new RuntimeException("Member not found for id: " + member.getId()));

        String encodedPassword = passwordEncoder.encode(newPassword);
        existingMember.setPassword(encodedPassword);

        try {
            memberRepository.save(existingMember);
        } catch (Exception e) {
            throw new RuntimeException("Error changing password", e);
        }
    }

    @Override
    public void deleteMember(Member member) throws Exception {
        Member existingMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new RuntimeException("Member not found for id: " + member.getId()));
        existingMember.setDeleted(true);
        try {
            memberRepository.save(existingMember);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting member", e);
        }
    }

    @Override
    public void resetPassword(Long memberId, String newPassword) throws Exception {
        Member existingMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found for id: " + memberId));

        String encodedPassword = passwordEncoder.encode(newPassword);
        existingMember.setPassword(encodedPassword);

        try {
            memberRepository.save(existingMember);
        } catch (Exception e) {
            throw new RuntimeException("Error resetting password", e);
        }
    }

    @Override
    public void processMemberApplication(MemberDto memberDto) throws Exception {
        if (memberDto.getRole().equalsIgnoreCase(MemberService.ROLE_ADMIN) && memberDto.isApproved()) {
            approveAdmin(memberDto);
        } else if (memberDto.isDeleted()){
            deleteUser(memberDto);
        }
        else {
            registerUser(memberDto);
        }
    }

    private void approveAdmin(MemberDto memberDto) throws Exception {
        Member member = getMemberByEmail(memberDto.getEmail());
        if (member != null) {
            member.setNewUser(false);
            member.setApproved(true);
            save(member);
        } else {
            throw new IllegalStateException("Member not found");
        }
    }

    private void deleteUser(MemberDto memberDto) throws Exception {
        Member member = getMemberByEmail(memberDto.getEmail());
        if (member != null) {
            member.setNewUser(false);
            member.setApproved(false);
            member.setDeleted(true);
            save(member);
        } else {
            throw new IllegalStateException("Member not found");
        }
    }

    private void registerUser(MemberDto memberDto) throws Exception {
        Member member = entityConverter.convertToEntity(memberDto, Member.class);
        registerMember(member, member.getPassword());
    }
}