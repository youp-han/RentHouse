package com.jjst.rentManagement.renthouse.module.Members.service;

import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Members.repository.MemberRepository;
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
        return passwordEncoder.matches(rawPassword, storedPassword);
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
            throw new RuntimeException(e.getMessage());
        }

    }
}
