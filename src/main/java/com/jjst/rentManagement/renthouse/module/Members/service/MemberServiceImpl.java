package com.jjst.rentManagement.renthouse.module.Members.service;

import com.jjst.rentManagement.renthouse.Service.MemberService;
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
    public Member getMemberByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    @Override
    public boolean checkEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    //Post
    @Override
    public void registerMember(Member member, String rawPassword){
        String email = member.getEmail().toLowerCase().trim();
        if(memberRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email Already Exists");
        }
        String encryptgedPassword = passwordEncoder.encode(rawPassword);
        member.setPassword(encryptgedPassword);
        memberRepository.save(member);
    }
}
