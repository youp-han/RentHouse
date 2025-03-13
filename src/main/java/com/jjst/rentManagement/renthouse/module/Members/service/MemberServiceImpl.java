package com.jjst.rentManagement.renthouse.module.Members.service;

import com.jjst.rentManagement.renthouse.Service.MemberService;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getAll(){ return memberRepository.findAll(); }
}
