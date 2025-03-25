package com.jjst.rentManagement.renthouse.controller;

import com.jjst.rentManagement.renthouse.service.MemberService;
import com.jjst.rentManagement.renthouse.dto.LoginDto;
import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
public class MemberRestController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/member/authenticate")
    @ResponseBody
    public ResponseEntity<String> loginAuthenticate(@RequestBody LoginDto lgDto, HttpServletRequest request) {
        Member member = memberService.getMemberByEmail(lgDto.getEmail());
        if (member != null) {
            if (memberService.authenticate(lgDto.getPassword(), member.getPassword())) {
                // Create a custom Principal or use Spring Security Authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                member,
                                null,
                                Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Create a new session and add the security context.
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
// 로그 추가
                System.out.println("Session ID: " + session.getId());
                System.out.println("SPRING_SECURITY_CONTEXT: " + session.getAttribute("SPRING_SECURITY_CONTEXT"));

                return ResponseEntity.ok("success");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }

    @PostMapping("/member/save")
    @ResponseBody
    public ResponseEntity<String> saveMemberApplication(@RequestBody Member member) {
        try {
            memberService.registerMember(member, member.getPassword());
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failure");
        }
    }

    @GetMapping("/member/check-email")
    public boolean checkEmail(@RequestParam String email){
        email = email.toLowerCase().trim();
        boolean result = memberService.checkEmail(email);
        return result;
    }

}
