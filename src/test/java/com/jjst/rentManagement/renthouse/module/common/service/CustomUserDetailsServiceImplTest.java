package com.jjst.rentManagement.renthouse.module.common.service;

import com.jjst.rentManagement.renthouse.module.common.enums.RoleType;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.members.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private CustomUserDetailsServiceImpl userDetailsService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setEmail("test@example.com");
        member.setPassword("password");
        member.setRole(RoleType.USER);
    }

    @Test
    void loadUserByUsername_userFound() {
        when(memberRepository.findByEmail("test@example.com")).thenReturn(member);

        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
        verify(memberRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void loadUserByUsername_userNotFound() {
        when(memberRepository.findByEmail("notfound@example.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("notfound@example.com");
        });

        verify(memberRepository, times(1)).findByEmail("notfound@example.com");
    }
}
