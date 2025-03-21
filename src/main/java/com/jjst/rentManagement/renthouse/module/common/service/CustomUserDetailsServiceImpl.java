package com.jjst.rentManagement.renthouse.module.common.service;

import com.jjst.rentManagement.renthouse.Service.CustomUserDetailsService;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import com.jjst.rentManagement.renthouse.module.Members.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch Member from the database
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Map Member entity to Spring Security's UserDetails
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword()) // Password is hashed
                .roles(member.getRole()) // Role from Member
                .build();
    }
}
