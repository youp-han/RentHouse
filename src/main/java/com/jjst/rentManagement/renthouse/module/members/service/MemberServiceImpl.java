package com.jjst.rentManagement.renthouse.module.members.service;

import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public List<Member> getNewMemberByIsNewTrue(){
        return memberRepository.findByisNewTrue();
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
    public void save(Member member) throws Exception {
        try{
            memberRepository.save(member);
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
}
