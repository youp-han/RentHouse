package com.jjst.rentManagement.renthouse.module.members.service;

import com.jjst.rentManagement.renthouse.dto.MemberDto;
import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import com.jjst.rentManagement.renthouse.module.members.repository.MemberRepository;
import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.util.EntityConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EntityConverter entityConverter;

    @InjectMocks
    private MemberServiceImpl memberService;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setId(1L);
        member.setEmail("test@example.com");
        member.setPassword("encodedPassword");
        member.setName("Test User");
    }

    @Test
    void registerMember_success() throws Exception {
        when(memberRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        memberService.registerMember(member, "password");

        verify(memberRepository, times(1)).save(any(Member.class));
        assertEquals("encodedPassword", member.getPassword());
    }

    @Test
    void registerMember_emailExists() {
        when(memberRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.registerMember(member, "password");
        });

        verify(memberRepository, never()).save(any(Member.class));
    }

    @Test
    void authenticate_success() {
        when(passwordEncoder.matches("rawPassword", "encodedPassword")).thenReturn(true);
        assertTrue(memberService.authenticate("rawPassword", "encodedPassword"));
    }

    @Test
    void authenticate_failure() {
        when(passwordEncoder.matches("rawPassword", "encodedPassword")).thenReturn(false);
        assertFalse(memberService.authenticate("rawPassword", "encodedPassword"));
    }

    @Test
    void updateMember_success() throws Exception {
        Member updatedInfo = new Member();
        updatedInfo.setId(1L);
        updatedInfo.setName("Updated Name");
        updatedInfo.setPhoneNumber("1234567890");

        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        memberService.updateMember(updatedInfo);

        verify(memberRepository, times(1)).save(member);
        assertEquals("Updated Name", member.getName());
        assertEquals("1234567890", member.getPhoneNumber());
    }

    @Test
    void changePassword_success() throws Exception {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(passwordEncoder.encode("newPassword")).thenReturn("newEncodedPassword");

        memberService.changePassword(member, "newPassword");

        verify(memberRepository, times(1)).save(member);
        assertEquals("newEncodedPassword", member.getPassword());
    }

    @Test
    void deleteMember_success() throws Exception {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        memberService.deleteMember(member);

        assertTrue(member.isDeleted());
        verify(memberRepository, times(1)).save(member);
    }

}
